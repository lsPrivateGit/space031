package com.itcast.observer_jvm;

import java.util.Observable;

/*
 * 定义一个被观察者对象，直接继承 Observable类，该类中包含了
 *  addObserver()  deleteObserver    notifyObservers()三个方法
 */
public class Subject extends Observable {
	
	public void update(String msg){
	
		//说明：这里一定要标记 setChanged()表示发生改变
		this.setChanged();
	
		this.notifyObservers(msg);
	}
}
