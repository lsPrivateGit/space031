package com.ticket;

public class Task implements Runnable {
	
	boolean running = true;   // 出现死循环
	int i = 0;
	
	@Override
	public void run() {
		while(running){
			i++;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Task task = new Task();
		Thread th = new Thread(task);
		th.start();
		Thread.sleep(10);
		task.running = false; // ----------这里 running的值不会生效，在主内存中，还是一样
		Thread.sleep(100);
		System.out.println(task.i);
		System.out.println("程序停止");
		//System.exit(0);
	}

}
