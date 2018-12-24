package com.concurrentlinkedqueue.demo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 * ConcurrentLinkedQueue 是线程安全的单向链表
 *  ConcurrentLinkedQueue是“线程安全”的队列，而LinkedList是非线程安全的。

 */
public class ConcurrentLinkedQueueTest {
	
	// 当是 LinkedList 链表时，运行会报错
	//private static Queue<String> queue = new LinkedList<>();
	
	
	private static Queue<String> queue  = new ConcurrentLinkedQueue<>();
	
	public static void main(String[] args) {
		new MyThread("a").start();
		new MyThread("b").start();
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
			while(i++ < 6){
				// “线程名” + "-" + "序号"
				String val = Thread.currentThread().getName()+i;
				queue.add(val);
				printAll();
			}
		}
	}
}
