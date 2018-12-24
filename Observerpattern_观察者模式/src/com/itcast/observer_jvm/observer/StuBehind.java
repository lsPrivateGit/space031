package com.itcast.observer_jvm.observer;

import java.util.Observable;
import java.util.Observer;

import com.itcast.observer_jvm.Subject;

public class StuBehind implements Observer {
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg+",StuBehind收到！");
	}
	
}
