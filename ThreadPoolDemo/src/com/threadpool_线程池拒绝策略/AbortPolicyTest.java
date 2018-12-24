package com.threadpool_线程池拒绝策略;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 线程池的拒绝策略 ：
 * AbortPolicy:当任务添加到线程池中被拒绝时，它将抛出 RejectedExecutionException异常
 */
public class AbortPolicyTest {
	/**
	 * 线程池默认的处理策略是 AbortPolicy
	 */
	private static final int THREAD_SIZE = 1; 
	private static final int CAPACITY = 1;
	
	public static void main(String[] args) throws Exception {
		// 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY)。
		
		ThreadPoolExecutor  pool = new ThreadPoolExecutor(THREAD_SIZE, THREAD_SIZE, 0, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<>(CAPACITY));
		
		 // 设置线程池的拒绝策略为"抛出异常"
		pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		
		// 新建10个任务，并将它们添加到线程池中。
		try {
			for(int i =0 ;i<10;i++){
				Runnable myrun  = new MyRunnable3("task-"+i);
				pool.execute(myrun);
			}
			pool.shutdown();
		} catch (RejectedExecutionException e) {
			e.printStackTrace();
		}
	}
}
class MyRunnable3 implements Runnable{
	
	private String name;
	public MyRunnable3(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		try{
			//System.out.println(Thread.currentThread().getName()+" is running ！");
			System.out.println(this.name+" is  running !");
			Thread.sleep(200);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
