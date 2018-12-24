package com.itcast.observer_byself.observer;

import java.util.ArrayList;
import java.util.List;

import com.itcast.observer_byself.IObserver;
import com.itcast.observer_byself.ISubject;

/*
 * 定义被观察者的实现类
 */
public class Subject  implements ISubject{

	private List<IObserver> mObservers ; //定义一个List集合泛型，并指向IObserver观察者对象
	//定义Subject构造方法，并实例化上面的集合
	public Subject() {
		mObservers = new ArrayList<>();
	}
	
	
	@Override
	public void registerObserver(IObserver o) {
		mObservers.add(o);
	}

	@Override
	public void removeObserver(IObserver o) {
		if(mObservers.contains(o)){
			mObservers.remove(o);
		}
	}

	@Override
	public void notifyObservers() {
		if(mObservers.size()>0){
			for(IObserver o:mObservers){
				o.update("老师来了！");
			}
		}
	}

}
