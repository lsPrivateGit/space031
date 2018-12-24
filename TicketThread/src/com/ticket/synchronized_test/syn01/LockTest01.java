package com.ticket.synchronized_test.syn01;

public class LockTest01 {
	
	//创建Something 类的两个对象 x  y
	Something x  = new Something(); 
	Something y  = new Something();
	
	public static void main(String[] args) {
		
		LockTest01 l = new LockTest01();
		
		l.test1();
	}
	
	
	/*
	 * 比较(01) x.isSyncA()与x.isSyncB() 
	 */
	private void test1(){
		
		//创建线程  t11  他们都共享 Something类的x实例
		Thread t11 = new Thread(new Runnable() {
			@Override
			public void run() {
				x.isSyncA();
			}
		}, "t11");
		
		//创建线程  t12   他们都共享 Something类的x实例
		Thread t12 = new Thread(new Runnable() {
			@Override
			public void run() {
				x.isSyncB();
			}
		}, "t12");
		
		
		t11.start();
		t12.start();
		
	}
	
	
}
