package com.strategy.stratedesign;

// 新客户小批量    具体策略类
public class NewLittleCustomer extends ContactPrice {

	

	@Override
	public double getPrice(double price) {
		System.out.println("新客户小批量");
		return 0.98*price;
	}

}
