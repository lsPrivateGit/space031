package com.ticket.synchronized_test.priority;

/*
 * 线程 优先级   ：从 1——10 ，默认是 5，“高优先级线程”会优先于“低优先级线程”执行。
 * Java中有两种线程：用户线程  和  守护线程。可以通过isDaemon()方法来区别它们：如果返回false，则说明该线程是“用户线程”；否则就是“守护线程”
 */
public class ThreadPriority {
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()
				  +"("+Thread.currentThread().getPriority()+ ")");     // 打印出 主线程main 的优先级  ---默认是5
		ThreadA t1 = new ThreadA("t1");
		ThreadA t2 = new ThreadA("t2");
		t1.setPriority(1);// 设置t1的优先级为1
		t2.setPriority(10);// 设置t2的优先级为10
		t1.start();
		t2.start();
	}
}

class ThreadA extends Thread{
	
	public ThreadA(String name) {
		super(name);
	}
	@Override
	public void run() {
		for(int i =0 ; i<5;i++){
			System.out.println(Thread.currentThread().getName()  
					+"("+Thread.currentThread().getPriority()+ ")"
					 +", loop "+i);
		}
	}
}