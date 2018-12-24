package com.strategy.stratedesign;


//测试
public class StrategyDemo {
	
	public static void main(String[] args) {
		
		Context context = new Context( new NewLittleCustomer());
		
		double price = context.getPrice(100);
		System.out.println(price);
	}
}
