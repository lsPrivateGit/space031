package com.tt.observer;

/*
 * 定义一个抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 */
public interface ISubject {
	public void registerObserver(IObserver o);
    public void removeObserver(IObserver o);
    public void notifyObserver();
}
