package com.ticket.synchronized_test.wait;

public class WaitTest{
		
	public static void main(String[] args) {
		
		ThreadA t = new ThreadA("t1");
		synchronized(t){
			try{
				// 启动线程 t
				System.out.println(Thread.currentThread().getName()+" start t");
				t.start();
				
				// 主线程等待t 通过notify()唤醒
				System.out.println(Thread.currentThread().getName()+" notify t");
				t.wait();
				
				System.out.println(Thread.currentThread().getName()+" cotinue");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class ThreadA  extends Thread{
	
	public ThreadA(String name) {
		super(name);
	}
	public void run(){
		synchronized(this){
			try {
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName()+" call notify()");
				// 唤醒当前线程
				 notify();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
