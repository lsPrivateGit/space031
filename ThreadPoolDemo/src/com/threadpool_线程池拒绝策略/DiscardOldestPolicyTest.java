package com.threadpool_线程池拒绝策略;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 线程池的拒绝策略 ：
 * DiscardOldestPolicy:当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中
 */
public class DiscardOldestPolicyTest {
	
	private static final int THREAD_SIZE = 1; 
	private static final int CAPACITY = 1;
	
	
	public static void main(String[] args) throws Exception {
	
		//创建线程池，线程池的 “最大池大小” 和“核心池大小”都为 1 (THREAD_SIZE),线程池的阻塞队列容量为1 CAPACITY
		ThreadPoolExecutor pool = new ThreadPoolExecutor(THREAD_SIZE, THREAD_SIZE, 0, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<>(1));
		
		//设置线程池的拒绝策略为 DiscardOldestPolicy
		pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
		
		//新建10个任务，并将他们添加到线程池中
		for(int i =0 ;i<10 ;i++){
			Runnable myrun = new MyRunnable2("task-"+i);
			pool.execute(myrun);
		}
		//关闭线程池
		pool.shutdown();
		
	}
}
class MyRunnable2 implements Runnable{
	
	private String name;
	public MyRunnable2(String name) {
		this.name = name;
		
	}
	
	@Override
	public void run() {
		try{
			System.out.println(this.name+" is running.");
			Thread.sleep(200);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
