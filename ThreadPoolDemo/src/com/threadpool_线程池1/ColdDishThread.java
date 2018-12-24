package com.threadpool_�̳߳�1;

public class ColdDishThread extends Thread{
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println("����׼�����");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 

	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		
		// ������ -- ����Ҫ�ȴ����صĽ��������Ҫ����join����
		Thread t1 = new ColdDishThread();
		t1.start();
		t1.join();
		
		// �Ȱ��� -- ����Ҫ�ȴ����صĽ��������Ҫ����join����
		Thread t2 = new BumThread();
		t2.start();
		t2.join();
		
		long end = System.currentTimeMillis();
		System.out.println("׼�����ʱ�䣺"+(end-start));
	}
}