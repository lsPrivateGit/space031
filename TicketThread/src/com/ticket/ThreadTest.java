package com.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadTest implements Runnable {
	int b = 100;
	public synchronized void m1(){
		//try{
			//Thread.sleep(5000);
			b = 1000;
			System.out.println(b);
		//}catch(InterruptedException e){
		//	e.printStackTrace();
		//}
	}
	public  void m2(){
		//try{
		//	Thread.sleep(1000);
			b = 2000;
			System.out.println("b = "+b);
	//	}catch (InterruptedException e) {
	//		e.printStackTrace();
	//	}
	}
	
	
	public void run(){
		try{
			Thread.sleep(1000);
			m1();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		ThreadTest test  = new ThreadTest();
		Thread t1 = new Thread(test);
		
		t1.start();
		
		
		test.m2();
		
		
		List<Object> list = new ArrayList<>();
		// 通过 Collections工具类 将 List 集合转换为线程安全的类，但是这种类型的集合对高并发支持的效率不高
		List<Object> list2 = Collections.synchronizedList(list);
		
		
	}
	
	
	
}
