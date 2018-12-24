package com.single.tt;

import java.util.concurrent.CountDownLatch;

import com.single.test.SingleTon01;
import com.single.test.SingleTon02;
import com.single.test.SingleTon03;
import com.single.test.SingleTon04;
import com.single.test.SingleTon05;
import com.single.test.SingleTon06;

/*
 * 并发性能测试
 */
public class TestSingleton3 {
	
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		
		int threadNum = 20;
		final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
		
		for(int i = 0;i<threadNum;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					for(int i = 0;i<1000000;i++){
	//					SingleTon01 s1 = SingleTon01.getInstance();
	//					SingleTon02 s2 = SingleTon02.getInstance();
	//					SingleTon03 s3 = SingleTon03.getInstance();
	//					SingleTon04 s4 = SingleTon04.getInstance();
	//					SingleTon05 s5 = SingleTon05.getInstance();
	//					SingleTon06 s6 = SingleTon06.INSTANCE;
					}
					countDownLatch.countDown();
				}
				
			}).start();
		}
		countDownLatch.await(); // main线程阻塞，直到计数器为0，才会往下执行
		long end = System.currentTimeMillis();
		System.out.println("总耗时："+(end - start));
	}
	
}
