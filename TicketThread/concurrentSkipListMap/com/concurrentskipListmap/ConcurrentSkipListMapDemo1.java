package com.concurrentskipListmap;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.Set;
import java.util.TreeMap;

/*
 * ConcurrentSkipListmap 是线程安全的哈希表，而Treemap是非线程安全的
 */
public class ConcurrentSkipListMapDemo1 {
	// 使用Treemap时会报错，线程不安排
	//private static Map<String, String> map = new TreeMap<>(); 
	
	// 使用concurrentSkipListmap不会报错，这个是线程安全的
	private static Map<String, String> map = new ConcurrentSkipListMap<>();
	
	
	
	public static void main(String[] args) {
		
		new MyTherad("t1").start();
		new MyTherad("t2").start();
	}
	
	
	private static class MyTherad extends Thread{
		
		public MyTherad(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			int i =0;
			while(i++<6){
				// “线程名” + "序号"
                String val = Thread.currentThread().getName()+i;
                map.put(val, "0");
                
                //通过Iterator遍历map
                printAll();
			}
		}
	}


	public static void printAll() {
		String key,value;
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String, String> entry = iterator.next();
			key = entry.getKey();
			value = entry.getValue();
			
			 System.out.print("("+key+", "+value+"), ");
		}
		System.out.println();
	}
}
