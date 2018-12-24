package com.single.test;

public class Hello implements ProxyInterface{

	private int a = 122;
	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	
	

	@Override
	public String toString() {
		return "Hello [a=" + a + "]";
	}

	@Override
	public String sayHello(Object o) {
		return o.toString();
	}

}
