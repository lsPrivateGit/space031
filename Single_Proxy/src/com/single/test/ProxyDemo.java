package com.single.test;

public class ProxyDemo implements ProxyInterface{

	public ProxyInterface target ;
	
	public ProxyDemo (ProxyInterface proxy){
		this.target = proxy;
	}
	
	@Override
	public String sayHello(Object o) {
		System.out.println("开始执行。。。。。");
        String a =target.sayHello(o);
		return a.toString();
	}

}
