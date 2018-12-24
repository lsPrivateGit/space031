package com.itcast.observer_jvm.observer;

import java.util.Observable;
import java.util.Observer;

public class StuLeft implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg+",StuLeft收到！");
	}
	
}

