package com.ticket.synchronized_test;

/*
 * 第一条：当一个线程访问“某对象”的 synchronized方法获取 synchronized代码块时，其他线程对该对象的
 * synchronized方法获取synchronized代码块的访问将被阻塞。
 */
public class Demo1_2 {
	
	public static void main(String[] args) {
		
		/*
		 * 说明：对 于 Demo1_2  ,synchronized(this) 中的this代表的是MyThread1_2 对象，
		 * 而t1 和 t2 是两个不同的MyThread1_2对象，因此t1 和 t2在执行synchronized(this)方法时
		 * 获取的是不同对象的同步锁。
		 * 对于 Demo1_1而言，synchronized(this)中的this代表的是MyThread1_1 对象；
		 * t1 和 t2 共同一个MyThread1_1对象，因此，一个线程获取了对象的同步锁，会造成另外一个线程等待；
		 */
		
		
		MyThread1_2 t1 = new MyThread1_2("t1"); //构造方法用于修改线程的名字：
		t1.start();
		MyThread1_2 t2 = new MyThread1_2("t2");
		t2.start();
		
		/*  输出：
		 *  t2:0
			t1:0
			t1:1
			t2:1
			t2:2
			t1:2
			t1:3
			t2:3
			t1:4
			t2:4
		 */
	}
}

/*
 * 继承Thread类来创建 一个线程类
 */
class MyThread1_2 extends Thread{
	
	public MyThread1_2(String name) { //用于改变线程的名字
		super(name);
	}
		
	@Override
	public void run() {
		
		synchronized(this){
			try{
				for(int i =0 ;i<5;i++){
					Thread.sleep(1000);
					System.out.println(this.getName()+":"+i);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}