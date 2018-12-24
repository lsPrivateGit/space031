package com.strategy.stratedesign;


//表示公共的方法 ，持有 封装了算法的 策略引用
public class Context {
	
	  //持有一个具体策略的对象
	private ContactPrice contactPrice;
	
	/**
     * 构造函数，传入一个具体策略对象
     * @param contactPrice    具体策略对象
     */
	public Context(ContactPrice contactPrice) {
		this.contactPrice = contactPrice;
	}
	 /**
     * 策略方法 ：获取价格
     */
	
	public double getPrice(double price) {
		return contactPrice.getPrice(price);
	}
	
}
