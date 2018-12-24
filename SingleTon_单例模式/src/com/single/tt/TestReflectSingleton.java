package com.single.tt;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.single.test.SingleTon01;

/**
 *  利用反射 破解饿汉式 单例模式 
 *  =========反射穿透====
 */
public class TestReflectSingleton {
	
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		//1、通过该类全路径获取该类的字节码对象
		Class<SingleTon01> clazz = (Class<SingleTon01>) Class.forName("com.single.test.SingleTon01");
		
		//2、获取该字节码对象的构造方法
		 Constructor<SingleTon01> constructor =  clazz.getDeclaredConstructor(null);
		 
		 //3、暴力反射
		 constructor.setAccessible(true);
		
		 SingleTon01 s1 = constructor.newInstance();
		 SingleTon01 s2 = constructor.newInstance();
		 System.out.println(s1);
		 System.out.println(s2);
		 System.out.println(s1==s2);
		 
	}
}
