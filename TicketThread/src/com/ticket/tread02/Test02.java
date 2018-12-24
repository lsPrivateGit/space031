package com.ticket.tread02;

public class Test02 {
	
	public int inc = 0;
	
	public synchronized void increse(){
		inc ++;
	}
	public static void main(String[] args) {
		final Test02 test02 = new Test02();
		for(int i =0;i<10;i++){
			new Thread(){
				public void run() {
					for(int j =0;j<1000;j++){
						test02.increse();
					}
				};
			}.start();
		}
		//保证前面的线程都执行完
		while(Thread.activeCount()>1){// 该方法返回活动线程的当前线程的线程组中的数量
			System.out.println("当前活动的线程数："+Thread.activeCount());
			Thread.yield();//礼让线程
			System.out.println(test02.inc);
		}
	}
}
