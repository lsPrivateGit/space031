package com.tt.observer.obimp;

import com.tt.observer.ISubject;

import java.util.ArrayList;
import java.util.List;
import com.tt.observer.IObserver;


/*
 * 被观察者，也就是微信公众号服务
 * 实现了ISubject接口，对ISubject接口的三个方法进行了具体实现
 */
public class WechatServer implements ISubject {

	private List<IObserver> lists;
	
	private String message;
	
	public WechatServer() {
		lists = new ArrayList<>();
	}
	
	
	@Override
	public void registerObserver(IObserver o) {
		lists.add(o);
	}

	@Override
	public void removeObserver(IObserver o) {
		if(lists.contains(o)){
			lists.remove(o);
		}
	}

	@Override
	public void notifyObserver() {
		for(int i=0;i<lists.size();i++){
			lists.get(i).update(message);
		}
	}
	
	public void setInformation(String s){
		this.message= s;
		System.out.println("微信服务更新消息： " + s);
		//消息更新，通知所有观察者
		notifyObserver();
	}

}
