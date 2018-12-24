package com.tt.observer.obimp;

import com.tt.observer.IObserver;

/*
 * 定义具体观察者
 * 实现了IObserver接口，并重新update()方法
 */
public class User implements IObserver {

	private String name;
	private String message;
	
	public User(String name) {
		this.name = name;
	}
	
	
	
	@Override
	public void update(String message) {
		this.message = message;
		read();
	}

	
	public void read() {
		System.out.println(name + " 收到推送消息： " + message);
	}

}
