package com.ticket.synchronized_test.syn01;

public class LockTest03 {
	
	//创建Something 类的两个对象 x  y
	Something x  = new Something(); 
	Something y  = new Something();
	
	public static void main(String[] args) {
		LockTest03 l = new LockTest03();
		l.test3();
	}
	
	
	/*
	 * 比较 (03) x.cSyncA()与y.cSyncB()
	 */
	private void test3(){
		
		Thread t31 = new Thread(new Runnable() {
			@Override
			public void run() {
				x.cSyncA();
			}
		}, "t31");
		
		Thread t32 = new Thread(new Runnable() {
			@Override
			public void run() {
				y.sSyncB();
			}
		}, "t32");
		
		
		t31.start();
		t32.start();
	}
	
}
