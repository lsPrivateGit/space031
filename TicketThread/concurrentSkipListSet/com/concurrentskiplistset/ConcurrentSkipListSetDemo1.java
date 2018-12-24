package com.concurrentskiplistset;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

/*
 * ConcurrentSkipListSet 示例，它是线程安全的，底层是通过ConcurrentSkipListMap来实现的；
 * ConcurrentSkipListSet 是 线程安全的集合，而TreeSet是非线程安全的
 * 
 */
public class ConcurrentSkipListSetDemo1 {
	
	// 当 set是 TreeSet时，它是非线程安全的
//	private static Set<String> set = new TreeSet<>();
	
	private static Set<String> set = new ConcurrentSkipListSet<>();
	
	public static void main(String[] args) {
		new Mythread("a").start();
		new Mythread("b").start();
	}
	
	private static void  printAll(){
		String value = "";
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			value = iterator.next();
			System.out.print(value+",");
		}
		System.out.println();
	}
	
	private static class Mythread extends Thread{
		
		public Mythread(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			int i = 0;
			while (i++ < 10) {
				// “线程名” + "序号"
				String val = Thread.currentThread().getName() + (i%6);
				set.add(val);
				// 通过“Iterator”遍历set。
				printAll();
				}
		}
		
	}
	
}
