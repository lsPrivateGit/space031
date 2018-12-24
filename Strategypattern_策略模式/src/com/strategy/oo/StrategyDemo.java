package com.strategy.oo;

public class StrategyDemo {
	
	public static void main(String[] args) {
		ContactPrice con = new ContactPrice();
		double price = con.getPrice("新客户大批量", 100);
		System.out.println(price);
	}
}
