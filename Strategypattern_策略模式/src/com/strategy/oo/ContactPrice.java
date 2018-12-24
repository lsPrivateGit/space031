package com.strategy.oo;

/**
 * 
 *根据面向对象的方式来处理 根据不同客户和数量来获取价格
 */
public class ContactPrice {
	
	public double getPrice(String type,double price){
		
		if("新客户小批量".equals(type)){
			System.out.println("你是新客户小批量");
			return price*0.98;
		}else if ("新客户大批量".equals(type)) {
			System.out.println("你是新客户大批量");
			return price*0.9;
		}else if ("老客户小批量".equals(type)) {
			System.out.println("老客户小批量");
			return price*0.88;
		}else if ("老客户大批量".equals(type)) {
			System.out.println("老客户大批量");
			return price*0.8;
		}
		return price;
	}
}
