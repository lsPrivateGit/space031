package com.ticket;

public class Task2  implements Runnable{

	volatile boolean running = true;    // 加上 volatile关键字修饰后，使得 running的值再主内存中可以获取到最新值
	int i = 0;
	@Override
	public void run() {
		while(running){
			i++;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Task2 task2 = new Task2();
		Thread th = new Thread(task2);
		th.start();
		Thread.sleep(10);
		task2.running = false;
		Thread.sleep(100);
		System.out.println(task2.i);
		System.out.println("线程停止");
		
	}

}
