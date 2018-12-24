package com.itcast.observer_byself;

import com.itcast.observer_byself.observer.Subject;

/*
 * 测试类
 */
public class ClientDemo {
	
	public static void main(String[] args) {
		Subject subject = new Subject();
		StuFront stuFront = new StuFront();
		
		//1、注册观察者
		subject.registerObserver(stuFront);
		subject.registerObserver(new StuBehind());
		subject.registerObserver(new StuLeft());
		subject.registerObserver(new StuRight());
		
		subject.removeObserver(stuFront);//移除观察者，则不通知
		
		// 通知观察者
		subject.notifyObservers();
	}
}
