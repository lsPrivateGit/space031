package com.single.test;
/*
 * 单例模式：保证一个类中只存在一个对象的实例，节省开销
 * 未实现延迟加载
 */
public class SingleTon01 {
	
	/**
	 * 饿汉式：一上来就new 出对象实例     未实现延迟加载
	 * ============这种方式不太好，因为一创建该类的对象就会new对象，开销大
	 */
	
	//第二步：在该类中创建该实例对象
	private static SingleTon01 instance = new SingleTon01();
	
	//第一步：私有构造 ,目的是为了防止其他对象new该对类的实例对象出来
	private SingleTon01(){}
	
	
	//第三步：对外提供获取该实例的方法
	public static SingleTon01 getInstance(){
		return instance;
	}
	
	public static void main(String[] args) {
		SingleTon01 s = SingleTon01.getInstance();
		//SingleTon01 s2 = new SingleTon01();
		SingleTon01 s1 = SingleTon01.getInstance();
		System.out.println(s);
		//System.out.println(s2);
		System.out.println(s1);
	}
}
