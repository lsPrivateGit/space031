package com.threadpool_demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 线程池示例
 */
public class ThreadPoolTest1 {
	
	public static void main(String[] args) {
		
		
		
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(10);// 线程最多能运行2个线程
		
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		Thread t6 = new MyThread();
		
		 // 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		pool.execute(t6);
		
		//关闭线程池
		pool.shutdown();
	}
}

class MyThread extends Thread{
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" is running!");
	}
}