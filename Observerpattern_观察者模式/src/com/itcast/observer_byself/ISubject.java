package com.itcast.observer_byself;

/*
 * 自定义 被观察者对象接口
 */
public interface ISubject {
	
	/*
	 * @param IObserver o 观察者对象
	 * 注册观察者
	 */
	public void registerObserver(IObserver o);
	/*
	 * @param IObserver o 观察者对象
	 * 移除观察者
	 */
	public void removeObserver(IObserver o);
	/*
	 * 通知观察者
	 */
	public void notifyObservers();
}
