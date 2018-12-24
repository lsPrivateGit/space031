package com.ticket.tread;


/*
 * 使用 实现 Runnable 接口 方式来创建线程----------卖票
 */
public class MyThread02 {
	
	public static void main(String[] args) {
		
		MyThread2 m1 = new MyThread2();
	
		/*
		 *  启动3个线程t1,t2,t3(它们共用一个Runnable对象)，这3个线程一共卖10张票！
		 */
		Thread t1 = new Thread(m1);
		Thread t2 = new Thread(m1);
		Thread t3 = new Thread(m1);
		
		
		
		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThread2 implements Runnable{
	
	private int ticket = 10;
	
	@Override
	public void run() {
		
		for( int i=0;i<20;i++){
			if(this.ticket>0){ //要保证 ticket大于0，即还有剩票，否则会出现负数
				
				System.out.println(Thread.currentThread().getName()+"售票：ticket,剩余："+this.ticket--);
			}
		}
	}
	
}