package com.strategy.stratedesign;

//  老客户大批量        具体策略类
public class OldBigCusomer  extends ContactPrice{

	@Override
	public double getPrice(double price) {
		System.out.println("老客户大批量");
		return 0.8*price;
	}

}
