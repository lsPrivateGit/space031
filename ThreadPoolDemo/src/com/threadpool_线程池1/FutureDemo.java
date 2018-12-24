package com.threadpool_�̳߳�1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		
		// ������ 
		Callable ca1 = new Callable(){
 
			@Override
			public String call() throws Exception {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "����׼�����";
			}
		};
		FutureTask<String> ft1 = new FutureTask<String>(ca1);
		new Thread(ft1).start();
		
		// �Ȱ��� -- ����Ҫ�ȴ����صĽ��������Ҫ����join����
		Callable ca2 = new Callable(){
 
				@Override
				public Object call() throws Exception {
					try {
						Thread.sleep(1000*3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return "����׼�����";
			}
		};
		FutureTask<String> ft2 = new FutureTask<String>(ca2);
		new Thread(ft2).start();
		
		System.out.println(ft1.get());
		System.out.println(ft2.get());
		
		long end = System.currentTimeMillis();
		System.out.println("׼�����ʱ�䣺"+(end-start));
	}

}
