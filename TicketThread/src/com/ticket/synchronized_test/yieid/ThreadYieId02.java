package com.ticket.synchronized_test.yieid;

/*
 * 说明：
 *  wait():是让线程由“运行状态”进入到“等待(阻塞)状态，” yieid()则是让线程由“运行状态” 进入到"就绪状态";
 *  wait():会让当前线程释放它所持有的对象的同步锁，而yieid()则不会释放锁
 */
public class ThreadYieId02 {
	
	private static Object obj = new Object();
	
	public static void main(String[] args) {
		TheadA t1 = new TheadA("t1");
		TheadA t2 = new TheadA("t2"); 
		t1.start();
		t2.start();
	}


static class TheadA extends Thread{

	public TheadA(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		// 获取obj对象的同步锁
		synchronized(obj){
			for(int i =0;i<10;i++){
				try {
					Thread.sleep(1000);
					// this.getPriority()：获取当前线程的优先级
					 System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i); 
					 // i整除4时，调用yield
					 if(i%4==0){
						 Thread.yield();
					 }
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
 }
}

