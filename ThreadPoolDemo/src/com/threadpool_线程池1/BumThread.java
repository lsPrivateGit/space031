package com.threadpool_�̳߳�1;


public class BumThread extends Thread{
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000*3);
			System.out.println("����׼�����");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
}
