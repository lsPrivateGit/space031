package com.threadpool_demo2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 线程池的拒绝策略 ：
 * DiscardPolicy :当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务
 */
public class DiscardPolicyTest {
	
	private static final int THREADS_SIZE =1;
	private static final int CAPACITY = 1;
	
	public static void main(String[] args) throws Exception {
		
		// 创建线程池，线程池的最大池大小和 核心池大小 都为 THREADS_SIZE，线程池的阻塞队列容量为1CAPACITY
		ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<>(CAPACITY));
		// 设置线程池的拒绝策略为 "丢弃"
		pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
		
		// 新建10个任务，并将他们添加到线程池中
		for(int i=1;i<10;i++){
			Runnable myrun = new MyRunnable("task-"+i);
			pool.execute(myrun);
		}
		//关闭线程池
		pool.shutdown();
	}
}

class MyRunnable implements Runnable{
	private String name;
	public MyRunnable(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		try{
			//打印当前线程的名字
			System.out.println(this.name + " is running.");
			Thread.sleep(100);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}