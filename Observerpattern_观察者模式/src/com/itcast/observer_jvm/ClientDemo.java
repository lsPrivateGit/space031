package com.itcast.observer_jvm;

import com.itcast.observer_jvm.observer.StuBehind;
import com.itcast.observer_jvm.observer.StuFront;
import com.itcast.observer_jvm.observer.StuLeft;
import com.itcast.observer_jvm.observer.StuRight;

public class ClientDemo {
	
	public static void main(String[] args) {
		Subject subject = new Subject();
		
		StuFront front = new StuFront();
		StuBehind behind = new StuBehind();
		StuLeft left = new StuLeft();
		StuRight right = new StuRight();
		
		subject.addObserver(front);
		subject.addObserver(behind);
		subject.addObserver(left);
		subject.addObserver(right);
		
		subject.deleteObserver(front);//移除StuFront
		
		subject.update("老师来了");
		
		subject.notifyObservers();
	}
}	
