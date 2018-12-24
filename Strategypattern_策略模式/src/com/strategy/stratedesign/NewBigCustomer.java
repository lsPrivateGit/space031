package com.strategy.stratedesign;

//新客户大批量       具体策略类
public class NewBigCustomer  extends ContactPrice{

	
	@Override
	public double getPrice(double price) {
		System.out.println("新客户大批量");
		return 0.9*price;
	}

}
