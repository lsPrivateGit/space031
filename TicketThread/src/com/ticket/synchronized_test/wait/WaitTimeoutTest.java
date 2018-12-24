package com.ticket.synchronized_test.wait;

public class WaitTimeoutTest {
	
	public static void main(String[] args) {
		
		ThreadB t1 = new ThreadB("t1"); // 创建线程 t1
		
		synchronized(t1){ // 获取线程 t1的同步锁
			try{
				// 通过主线程 main 启动“线程t1”
				 System.out.println(Thread.currentThread().getName() + " start t1 ");
				 t1.start();
				 // 主线程等待t1通过nofity()唤醒或者 nofifyAll()唤醒 或者 超过 3000ms延时，然后才被唤醒
				 System.out.println(Thread.currentThread().getName() + " call wait ");
				 t1.wait(5000); // 注意：这里t1.wait(3000) 是通过 线程 t1让当前活动的线程等待，即让主线程 main等待
				 
				 
				 // 超过 3000ms后(超时)，主线程重新被唤醒
				 System.out.println(Thread.currentThread().getName() + " coutinue ");
				 
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class ThreadB extends Thread{
	
	public ThreadB(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		 System.out.println(Thread.currentThread().getName() + " run "); // 大约3秒之后...输出“main continue”
		// 死循环，不断运行。
		 while(true)
			 ;
	}
}