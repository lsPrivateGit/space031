package com.itcast.observer_byself;

/*
 * 定义观察者接口，当被观察者对象发生变化时，
 *   就调用这个方法发送消息给观察者
 */
public interface IObserver {
	
	public void update(Object obj);
}

/*
 * 观察者对象
 * 表示前面的同学 StuFront
 */
class StuFront implements IObserver{
	@Override
	public void update(Object obj) {
		System.out.println("StuFront收到,"+obj);
	}
	
}
/* 观察者对象
 * 表示后面的同学 StuBehind
 */
class StuBehind implements IObserver{
	@Override
	public void update(Object obj) {
		System.out.println("StuBehind收到,"+obj);
	}
	
}
/* 观察者对象
 * 表示左边的同学 StuLeft
 */
class StuLeft implements IObserver{
	@Override
	public void update(Object obj) {
		System.out.println("StuLeft收到,"+obj);
	}
	
}
/* 观察者对象
 * 表示右边的同学 StuRight
 */
class StuRight implements IObserver{
	@Override
	public void update(Object obj) {
		System.out.println("StuRight收到,"+obj);
	}
	
}

