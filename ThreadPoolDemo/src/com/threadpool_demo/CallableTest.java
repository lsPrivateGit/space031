package com.threadpool_demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Callable 是一个接口，它只包含一个call()方法。Callable是一个返回结果并且可能抛出异常的任务
 * Future 是一个接口，它用于表示异步计算的结果。提供了检查计算是否完成的方法，以等待计算的完成，并获取计算的结果。
 */
public class CallableTest {

	public static void main(String[] args) throws Exception {
		 // 创建一个线程池
		ExecutorService pool = Executors.newSingleThreadExecutor();
		// 创建有返回值的任务
		Callable c1 = new MyCallable();
		//执行任务并获取future对象
		Future f1 = pool.submit(c1);
		
		System.out.println(f1.get());
		
		pool.shutdown();
	}
}

class MyCallable implements Callable {

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i =0 ;i<100;i++){
			sum += i;
		}
		return sum; //Integer.valueOf(sum);
	}
	
}