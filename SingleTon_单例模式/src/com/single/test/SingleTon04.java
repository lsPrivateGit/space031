package com.single.test;

/*
 *  使用  双重检查加锁机制 来保证现场安全
 */
public class SingleTon04 {
	
	//第二步：定义该对象，初始化值为null
	private volatile static SingleTon04 instance = null;  //加上 voloatile关键字修饰该对象
	
	//第一步：私有构造
	private SingleTon04(){}
	
	
	//第三步：对外提供获取来实例的公共方法
	public static SingleTon04 getInstance(){
		/**
		 * 使用 双重检查加锁机制
		 */
		if (instance == null){ 
			synchronized (SingleTon04.class) { // 在这里加上锁，当前字节码对象
				if(instance == null){
					 //不加volitale关键字的话有可能会出现尚未完全初始化就获取到的情况 ll。原因是内存模型允许无序写入 
					instance = new SingleTon04();
				}
			}
		}
		
		return instance;
	}
	
}
