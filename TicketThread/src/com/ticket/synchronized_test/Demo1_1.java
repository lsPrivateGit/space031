package com.ticket.synchronized_test;

/*
 * 第一条：当一个线程访问“某对象”的 synchronized方法获取 synchronized代码块时，其他线程对该对象的
 * synchronized方法获取synchronized代码块的访问将被阻塞。
 */
public class Demo1_1 {
	
	public static void main(String[] args) {
		
		Runnable r  = new MyThread1_1();// 新建“Runnable对象”
		
		/*
		 * 对于 Demo1_1而言，synchronized(this)中的this代表的是MyThread1_1 对象；
		 * t1 和 t2 共同一个MyThread1_1对象，因此，一个线程获取了对象的同步锁，会造成另外一个线程等待；
		 *  说明：对 于 Demo1_2  ,synchronized(this) 中的this代表的是MyThread1_2 对象，
		 * 而t1 和 t2 是两个不同的MyThread1_2对象，因此t1 和 t2在执行synchronized(this)方法时
		 * 获取的是不同对象的同步锁。
		 */
		Thread t1 = new Thread(r, "t1");
		Thread t2 = new Thread(r, "t2");
		/**
		 * 线程 t1 和 t2 共享  r对象的同步锁，所以当一个线程运行时，另一个线程必须等待当前线程释放该对象的同步锁才能执行
		 * **/
		t1.start();
		t2.start();
		
		/*  输出形式：
		 * t1:0
			t1:1
			t1:2
			t1:3
			t1:4
			t2:0
			t2:1
			t2:2
			t2:3
			t2:4
		 */
	}
}

/*
 * 实现 runnable接口定义线程类 MyThread1_1
 */
class MyThread1_1 implements Runnable{
	
	@Override
	public void run() {
		synchronized (this) { //表示当前类的对象
			try {
				for(int i =0 ;i<5;i++){
					Thread.sleep(1000);//休眠100ms
					System.out.println(Thread.currentThread().getName()+":"+i);
					}	
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
