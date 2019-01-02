package com.single.tt;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.single.test.SingleTon02;

/*
 * 利用反射 来破解 懒汉式  单例模式
 * ========在 懒汉式SingleTon02.java 的构造方法中，加了判断，如果对象不为null，则不能
 * 在创建对象了，抛异常
 */
public class TestReflectSingleton02 {
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		
		
		
		
		
		
		
		
		//1、第一步：通过全路径获取该类的字节码对象
		Class<SingleTon02> clazz = (Class<SingleTon02>) Class.forName("com.single.test.SingleTon02");
		
		//2、第二步：获取该类的构造方法(getDeclaredContructor获取的是指定参数的所有构造方法)
	   Constructor<SingleTon02>	 constructor= clazz.getDeclaredConstructor(null);
	   //3、第三步：暴力反射，设置该属性，则私有的构造方法，也可以获取
	   constructor.setAccessible(true);
	   SingleTon02 s3 = constructor.newInstance();
	   SingleTon02 s4 = constructor.newInstance();
	   System.out.println(s3);
	   System.out.println(s4);
	   System.out.println(s3==s4);
	   
	   
	   /*
	    * 但是无法阻止反射发生在初始化之前
	    */
	   SingleTon02 s1 = SingleTon02.getInstance();
		SingleTon02 s2 = SingleTon02.getInstance();
		System.out.println(s1);
		System.out.println(s2);
	}
}
