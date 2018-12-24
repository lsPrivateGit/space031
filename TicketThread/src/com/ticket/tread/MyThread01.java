package com.ticket.tread;


/*
 * 使用继承 thread 方式来创建线程----------卖票
 */
public class MyThread01 {
	
	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();
		MyThread t3 = new MyThread();
		/*
		 * 这里创建三个线程，每个线程都卖了10张票
		 */
		t1.start();
		t2.start();
		t3.start();
	}
}

/**
 * 继承 Thread 方式来创建线程
 */
class MyThread extends Thread{
	
	private int tickek = 10;
	
	@Override //重写run方法
	public void run() {
		for( int i =0;i<20;i++){
			
			if(this.tickek>0){ //如果票大于0才能卖，否则会出现负数
				
				System.out.println(this.getName()+"售票：ticket，剩余："+this.tickek--);
			}
		}
	}
}

