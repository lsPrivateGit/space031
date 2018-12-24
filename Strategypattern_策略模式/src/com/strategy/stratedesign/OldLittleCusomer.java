package com.strategy.stratedesign;

//老客户小批量       具体策略类
public class OldLittleCusomer extends ContactPrice {

	@Override
	public double getPrice(double price) {
		System.out.println("老客户小批量");
		return 0.88*price;
	}

}
