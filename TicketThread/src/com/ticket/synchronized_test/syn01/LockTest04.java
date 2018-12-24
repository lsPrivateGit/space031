package com.ticket.synchronized_test.syn01;

public class LockTest04 {
	//创建Something 类的两个对象 x  y
	Something x  = new Something(); 
	Something y  = new Something();
	
	public static void main(String[] args) {
		LockTest04 l = new LockTest04();
		l.test4();
	}
	/*
	 * 比较：x.isSyncA()与Something.cSyncA()
	 */
	private void test4(){
		
		Thread t41 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				x.isSyncA();
			}
		}, "t41");
		
		Thread t42 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Something.cSyncA();
			}
		}, "t42");
		
		t41.start();
		t42.start();
		
	}
}
