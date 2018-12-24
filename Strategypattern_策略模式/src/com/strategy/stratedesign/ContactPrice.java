package com.strategy.stratedesign;

/*
 * 定义获取价格的抽象类 ,里面定义一个获取价格的方法
 * 
 * 抽象策略类
 */
public abstract class ContactPrice {
	
	
	/**
	 * 策略方法
	 * @param price
	 * @return
	 */
	public abstract double getPrice(double price);

	
	
	
	
	
}
