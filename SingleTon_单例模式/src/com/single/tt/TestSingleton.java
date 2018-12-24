package com.single.tt;

import com.single.test.SingleTon01;
import com.single.test.SingleTon02;
import com.single.test.SingleTon04;
import com.single.test.SingleTon05;
import com.single.test.SingleTon06;

/*
 * 测试单例模式是否相等
 */
public class TestSingleton {
	public static void main(String[] args) {
		
	
	 /**
     * 饿汉式
     */
	  SingleTon01 singleTon01_01 = SingleTon01.getInstance();
	  SingleTon01 singleTon01_02 = SingleTon01.getInstance();
	  System.out.println(singleTon01_01 == singleTon01_02);//true
	  
	/**
     * 懒汉式
     */
	  SingleTon02 singleTon02_01 = SingleTon02.getInstance();
	  SingleTon02 singleTon02_02 = SingleTon02.getInstance();
	  System.out.println(singleTon01_01 == singleTon01_02);//true

	 /**
     * 双重检测锁式
     */
	  SingleTon04 singleTon04_01 = SingleTon04.getInstance();
	  SingleTon04 singleTon04_02 = SingleTon04.getInstance();
	  System.out.println(singleTon04_01 == singleTon04_02);//true
	 /**
     * 静态内部类式
     */
	  SingleTon05 singleTon05_01 = SingleTon05.getInstance();
	  SingleTon05 singleTon05_02 = SingleTon05.getInstance();
	  System.out.println(singleTon05_01 == singleTon05_02);//true
	 /**
      * 枚举式
      */
	  SingleTon06 singleTon06_01 = SingleTon06.INSTANCE;
	  SingleTon06 singleTon06_02 = SingleTon06.INSTANCE;
	 /*
      * 枚举型的任何成员类型都是类实例的类型
      */
	 System.out.println(singleTon06_01.getClass());  //枚举型的任何成员类型都是类实例的类型
	 System.out.println(singleTon06_01 == singleTon06_02);//true

	}
}
