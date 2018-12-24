package com.linkedblockingdeque_test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/*
 * LinkedBlockingDeque 是线程安全的双向队列，而LinkedList是非线程安全的
 */
public class LinkedblockingdequeTest {
	
	// 当 queue是 LinkedList链表时，运行保存，它是非线程安全的
	//private static Queue<String> queue = new LinkedList<>();
	
	private static Queue<String> queue = new LinkedBlockingDeque<>();
	
	public static void main(String[] args) {
		new MyThread("a").start();;
		new MyThread("b").start();;
	}
	
	private static void printAll(){
		String value = "";
		Iterator<String> iterator = queue.iterator();
		while(iterator.hasNext()){
			value = iterator.next();
			 System.out.print(value+", ");
		}
		System.out.println();
	}
	
	private static class MyThread extends Thread{
		
		public MyThread(String name) {
			super(name);
		}
		@Override
		public void run() {
			int i = 0;
			while(++i<6){
				// “线程名” + "-" + "序号"
				String val = Thread.currentThread().getName()+i;
				 // 通过“Iterator”遍历queue。
				queue.add(val);
				printAll();
			}
		}
	}
}
