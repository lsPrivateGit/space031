package com.tt.observer;

import com.tt.observer.obimp.User;
import com.tt.observer.obimp.WechatServer;

public class Test {
	
	public static void main(String[] args) {
		
		WechatServer server = new WechatServer();
		
		IObserver userZhang = new User("张三");
		IObserver userLi = new User("李四");
		IObserver userWang = new User("王五");
		
		server.registerObserver(userZhang);
		server.registerObserver(userLi);
		server.registerObserver(userWang);
		
		server.setInformation("PHP是世界上最好用的语言！");
		System.out.println("----------------------------------------------");
		
		server.removeObserver(userLi); //移除了userLi
		
		server.setInformation("Java才是世界上最好用的语言！");//并重新发布了消息
		
		
	}
}
