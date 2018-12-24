package com.ticket.tread;

/*
 * start()和 run()方法区别：
 * start():用于启动一个新线程，新线程会执行相应的run()方法，start()方法不能被重复执行；
 * run():该方法和普通的方法一样，可以被重复调用。单独调用run()方法的话，会在当前线程中执行run()，而不会启动新线程
 */
public class Demo {
	
	public static void main(String[] args) {
		
		/*
		 * 结果说明：
		 *(01) Thread.currentThread().getName()是用于获取“当前线程”的名字。当前线程是指正在cpu中调度执行的线程。
		 *(02) mythread.run()是在“主线程main”中调用的，该run()方法直接运行在“主线程main”上。
		 *(03) mythread.start()会启动“线程mythread”，“线程mythread”启动之后，会调用run()方法；此时的run()方法是运行在“线程mythread”上。
		 */
		
		MyThread03 mythread = new MyThread03("myThread03");
		
		System.out.println(Thread.currentThread().getName()+" call mythread start");
		mythread.start();//用于启动线程；
		//mythread.start();//用于启动线程；-----多次执行会报错
		
		System.out.println(Thread.currentThread().getName()+" call mythread run");
		mythread.run();//用于调用run方法，可多次执行
//		mythread.run();//用于调用run方法，可多次执行
//		mythread.run();//用于调用run方法，可多次执行
		
	}
}

/*
 * 定义一个线程
 */
class MyThread03 extends Thread{
	
	public MyThread03(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" is running ");
	}
}