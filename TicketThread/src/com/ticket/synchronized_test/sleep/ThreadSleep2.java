package com.ticket.synchronized_test.sleep;

/*
 * sleep()和wait()方法的比较：
 * wait()作用是让当前线程由运行状态进入到等待阻塞状态的同时，也会释放同步锁；
 * 而sleep()的作用也是让当前线程由运行状态进入到休眠(阻塞)状态；
 * 但是wait会释放对象的同步锁，而sleep不会释放锁
 */
public class ThreadSleep2 {
	
	private  static Object obj = new Object();
	public static void main(String[] args) {
		
		// 说明：当启动线程后，当执行到t1休眠时，t1并没有释放当前对象锁，而是继续等待休眠时间过了之后再继续执行
		// 当把synchronized(obj) 注释掉后， 三个线程会互相切换
		ThreadA t1 = new ThreadA("t1");
		ThreadA t2 = new ThreadA("t2");
		ThreadA t3 = new ThreadA("t3");
		t1.start();
		t2.start();
		t3.start();
	}
	
	static class ThreadA extends Thread{

		public ThreadA(String name) {
			super(name);
		}
		@Override
		public void run() {
			//synchronized(obj){
				for(int i =0;i<10;i++){
					try {
						Thread.sleep(1000);
						// this.getPriority()：获取当前线程的优先级
						 System.out.printf("%s:%d\n", this.getName(),  i); 
						 // i整除4时，调用yield
						 if(i%4==0){
							Thread.sleep(3000);
						 }
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		//}
		
	}
	
}
