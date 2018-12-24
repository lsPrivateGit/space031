package com.linkedblcokingqueue.demo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * LinkedBlockingQueue是线程安全的，它是单链表实现
 */
public class LinkedBlockingQueueDemo1 {
	// 当 Queue队列是 LinkedList链表形式时，它是非线程安全的，会报错
	//	private static Queue<String> queue = new LinkedList<>();
		
		private static Queue<String> queue = new LinkedBlockingQueue();
		
		public static void main(String[] args) {
			new Mythread("a").start();
			new Mythread("b").start();
		}
		
		
		private static class Mythread extends Thread{
			
			public Mythread(String name) {
				super(name);
			}
			
			@Override
			public void run() {
				int i =0;
				while(i++<6){
					// “线程名” + "序号"
	                String val = Thread.currentThread().getName()+i;
	                queue.add(val);
	                //通过Iterator遍历queue
	                printAll();
				}
			}
		}


		private static  void printAll() {
			String value = "";
			Iterator<String> iterator = queue.iterator();
			while(iterator.hasNext()){
				value = iterator.next();
				System.out.print(value+", ");
			}
			System.out.println();
		}
		
	}
