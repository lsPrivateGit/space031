package com.single.tt;

import com.single.test.SingleTon01;

/*
 * 测试
 */
public class GetInstanceDemo {
	
	public static void main(String[] args) {
		SingleTon01 instance01 = SingleTon01.getInstance();
		SingleTon01 instance02 = SingleTon01.getInstance();
		
		

		System.out.println(instance01);
		System.out.println(instance02);
		System.out.println(instance01==instance02);//true
		
	
		
	}
}


//class MyThread01 extends Thread{
//	
//	@Override
//	public void run() {
//		SingleTon01 instance01 = SingleTon01.getInstance();
//	}
//}
//
//class MyThread02 extends Thread{
//	@Override
//	public void run() {
//		SingleTon01 instance02 = SingleTon01.getInstance();
//	}
//}





