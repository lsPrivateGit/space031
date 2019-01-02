package com.single.test;

import java.io.Serializable;

/*
 * 单例模式：保证一个类中只存在一个对象的实例，节省开销
 */
public class SingleTon03 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 懒汉式：先定义该对象实例，但不new出来
	 * ======  这种方式虽然节省开销，但是在多线程环境下，会出现多个对象，不能保证是单例 ,
	 * ========= 在获取实例的方法上，加上 synchronized 关键字，锁机制
	 */
	
	//第二步：定义该对象，初始化值为null
	private static SingleTon03 instance = null;
	
	//第一步：私有构造
	private SingleTon03(){}
	
	//第三步：对外提供获取来实例的公共方法
	
	/***说明：在获取实例的方法上加上synchronized关键字，
	 * 虽然可以保证现场安全，但是在方法上加上锁，非常耗费内存，也不建议使用***/
	
	public static synchronized SingleTon03 getInstance(){
		//在这里判断下是否为空，如果为空，则new该实例对象
		if (instance == null){
			instance = new SingleTon03();
		}
		return instance;
	}
	
	
	/*
	 * 可以看到，当通过序列化将对象写入到文件中，然后在从中读取出来的对象，不是同一个对象
	 * 这就是反序列化破解，如何防止这种破解单例模式呢？我们采用重写反序列化方法
	 * -----readResolve():最终防止单例被破解的代码：
	 */
	//反序列化时，如果定义了 readResolve()方法，则直接返回此方法中指定的对象，而不在需要单独创建对象
	private Object readResolve(){
		return instance;
	}
	
	
}
