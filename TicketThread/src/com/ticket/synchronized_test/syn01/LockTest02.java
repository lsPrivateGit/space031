package com.ticket.synchronized_test.syn01;

public class LockTest02 {
	//创建Something 类的两个对象 x  y
	Something x  = new Something(); 
	Something y  = new Something();
	
	public static void main(String[] args) {
		LockTest02 l = new LockTest02();
		l.test2();
	}
	/*
	 * 比较 (02) x.isSyncA()与y.isSyncA()
	 */
	private void test2(){
		
		//创建线程 t21
		Thread t21 = new Thread(new Runnable() {
			@Override
			public void run() {
				x.isSyncA();
			}
		}, "t21");
		//创建线程 t22
		Thread t22 = new Thread(new Runnable() {
			@Override
			public void run() {
				y.isSyncA();
			}
		}, "t22");
		
		t21.start();
		t22.start();
	}
		
}
