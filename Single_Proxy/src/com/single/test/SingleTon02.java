package com.single.test;
/*
 * 单例模式：保证一个类中只存在一个对象的实例，节省开销
 * 实现延迟加载
 * 线程安全但牺牲高并发性能
 */
public class SingleTon02 {

	/**
	 * 懒汉式：先定义该对象实例，但不new出来
	 * ======  这种方式虽然节省开销，但是在多线程环境下，会出现多个对象，不能保证是单例
	 */
	
	//第二步：定义该对象，初始化值为null
	private static SingleTon02 instance = null;
	
	//第一步：私有构造
	private SingleTon02(){
		/**
		 * 如果有反射进入构造器，判断后抛异常，这样一旦初始化 instance 对象
		 * 反射调用便会被阻止，初始化之前还是可以被反射的
		 */
		if(instance !=null){
			throw new RuntimeException();
		}
	}
	
	//第三步：对外提供获取来实例的公共方法
	public static SingleTon02 getInstance(){
		//在这里判断下是否为空，如果为空，则new该实例对象
		if (instance == null){
			instance = new SingleTon02();
		}
		return instance;
	}
	public static void main(String[] args) {
		SingleTon02 s = new SingleTon02();
		SingleTon02 s2 = SingleTon02.getInstance();
		SingleTon02 s1 = new SingleTon02();
		System.out.println(s);
		System.out.println(s2);
		System.out.println(s1);
	}
	
}
