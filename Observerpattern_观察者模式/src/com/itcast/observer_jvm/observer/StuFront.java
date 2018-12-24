package com.itcast.observer_jvm.observer;

import java.util.Observable;
import java.util.Observer;

/*
 * 定义 观察者对象 ，直接实现 Observer接口即可
 */
public class StuFront implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg+",StuFront收到！");
	}
	
}
