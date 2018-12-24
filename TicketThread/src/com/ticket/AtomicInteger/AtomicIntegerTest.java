package com.ticket.AtomicInteger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * AtomicInteger 类的使用：用于高并发
 */
public class AtomicIntegerTest {
	
	public static final AtomicInteger atomicInteger = new AtomicInteger(0);
	
	public static void main(String[] args) throws InterruptedException {
		
		automicIntegerTest();
		Thread.sleep(3000);
		System.out.println("最终结果："+ atomicInteger.get());
	}

	private static void automicIntegerTest() {
		ExecutorService executorServce = Executors.newFixedThreadPool(10000);
		for(int i =0;i<10000;i++){
			executorServce.execute(() ->{     // jdk 8 写法 
				for(int j=0;j<4;j++){
					System.out.println(atomicInteger.getAndIncrement());
				}
			});
			
		}
		executorServce.shutdown();
	}
	
}
