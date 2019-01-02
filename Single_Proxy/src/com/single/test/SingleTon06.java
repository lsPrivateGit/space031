package com.single.test;

/*
 * 使用枚举方式创建单例模式
 * 未延迟加载
 * 线程安全
 * 原生防止反射与反序列化击穿
 */
public enum SingleTon06 {
	
	INSTANCE;//枚举中都是定义常量
	
	public static Object geInstance(){
		// 添加其他功能逻辑。。。。。
		
		return null;
	}
}
