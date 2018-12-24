package com.single.test;

/*
 * 使用静态内部类 单例模式
 * 线程安全，实现延时加载
 */
public class SingleTon05 {
		
	//1、第一步：私有构造
	private SingleTon05(){}
	
	/*
	 * 2、定义一个内部类，用于创建单例SingleTon05的实例对象
	 *  说明：外部类初始化的时候不会初始化内部类，只有当调用getInstance()方法的时候才会初始化
	 */
	public static class InstanceInner{
		//在内部类中new出出外部类的实例对象
		public static SingleTon05 instance = new SingleTon05();
	}
	
	//3、第三步：对外提供一个获取该实例对象的方法
	public static SingleTon05 getInstance(){
		//直接使用 InstanceInner.对象的形式来获取该实例对象
		return InstanceInner.instance;
	}
	
	
}
