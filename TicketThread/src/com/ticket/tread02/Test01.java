package com.ticket.tread02;

/*
 * volatile 关键字使用说明：
 */
public class Test01 {
	
	public volatile int inc = 0;
	
	public void increse() {
		inc ++;
	}
	public static void main(String[] args) {
		// System.out.println("当前活动的线程数："+Thread.activeCount());
		
		final Test01 test01 = new Test01();
		
		for( int i = 0;i<10;i++){
			/*
			 * 开启了10个线程来执行
			 */
			new Thread(){
				public void run() {
					for( int j=0;j<1000;j++){
						test01.increse();
					}
				};
			}.start();
		}
		while(Thread.activeCount()>1){ //该方法返回活动线程的当前线程的线程组中的数量
			System.out.println("当前活动的线程数："+Thread.activeCount());
			Thread.yield();//礼让线程
			System.out.println(test01.inc);
		}
	}
}
