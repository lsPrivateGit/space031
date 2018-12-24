package com.list_saft;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * CopyOnWriteArrayList 线程安全的 类 示例（相比较于Arraylist）
 *  CopyOnWriteArrayList是“线程安全”的动态数组，而ArrayList是非线程安全的。
 *  
 *    (01) 当list是CopyOnWriteArrayList对象时，程序能正常运行。
 *    (02) 当list是ArrayList对象时，程序会产生ConcurrentModificationException异常。
 */
public class CopyOnWriteArraylistTest1 {
	// 当 List实现类是ArrayList时，则会抛出异常；这是 Java 中的 fail-fast机制（错误检测机制），因为Arraylist是线程不安全的，不支持多个线程同时操作
	//public static List<String> list = new ArrayList<>(); 
	
	// 改为 CopyWriteArrayLis 时，则正常，因为 它是线程安全的
	public static List<String> list = new CopyOnWriteArrayList<>();
	
	public static void main(String[] args) {
		// 同时启动两个线程对list进行操作！
		new MyThread("ta").start();
		new MyThread("tb").start();
	}


	private static class MyThread extends Thread{
		
		public MyThread(String name) {
			super(name);
		}
		public void run(){
			int i = 0 ;
			while(i++ < 6){
				// 线程名   +  “-” +i
				 String val = Thread.currentThread().getName()+"-"+i;
				 
				 list.add(val);
				 
				 //打印输出数组
				 printAll();
			}
		}
	}

	// 遍历输出数组
	public  static void printAll() {
		String value = null;
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			value = iterator.next();
			System.out.print(value+"-");
		}
		System.out.println();
	}
}
