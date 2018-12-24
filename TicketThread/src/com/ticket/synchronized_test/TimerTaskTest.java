package com.ticket.synchronized_test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器 Timer 说明
 */
public class TimerTaskTest {
	
	public static int count = 1;

	public static void main(String[] args) {
		
//		Timer timer  = new Timer();
//		timer.schedule(new TimerTask() {
//			
//			@Override
//			public void run() {
//				try{
//					System.out.println("bombing ！");
//				}catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}, 2000,3000); // 从firstTime时刻开始，每隔period毫秒执行一次。
		
		
		
		
		/*
		 * 功能说明：每隔2s，输出一次打印；然后再每隔4s，又输出打印一次；以此类推。。。。。
		 */
		class MyTimeTask  extends TimerTask{
			@Override
			public void run() {
				count = count % 2 +1;
				new Timer().schedule(new MyTimeTask(), 2000*count );
				System.out.println("bombing ！");
				
			}
		}
		
		new Timer().schedule(new MyTimeTask(), 2000);
		
		
		
		while(true){
			try {
				Thread.sleep(1000);
				System.out.println(new Date().getSeconds());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
