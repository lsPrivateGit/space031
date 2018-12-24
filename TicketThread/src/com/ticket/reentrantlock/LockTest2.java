package com.ticket.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest2 {
	public static void main(String[] args) {
		
		Dept2 dept = new Dept2();
		Producer1 mPro = new Producer1(dept);
		Customer1 mCus = new Customer1(dept);
		
		
		mPro.produce(60);
		mPro.produce(120);
		mCus.consume(90);
		mCus.consume(150);
		mPro.produce(110);
	}
}
// 仓库
class Dept2{
	private int size;  // 仓库实际数量
	private Lock lock; //独占锁
	
	public Dept2() {
		this.size = 0;
		this.lock = new ReentrantLock();
	}
	
	// 生产的方法
	public void produce(int val){
		
		size +=val;
		System.out.printf("%s produce(%d) --> size=%d\n", Thread.currentThread().getName(), val, size);
	}
	
	// 消费 方法
	public void consume(int val){
		size -= val;
		System.out.printf("%s consume(%d) <-- size=%d\n",  Thread.currentThread().getName(), val, size);
	}
}

// 生产者
class Producer1{
	private Dept2 dept;
	
	public Producer1(Dept2 dept) {
		this.dept = dept;
	}
	// 生产产品 ：新建一个线程向仓库中生产产品
	public void produce(final int val){
		new Thread(){
			public void run(){
				dept.produce(val);
			}
		}.start();
	}
	
}
//消费者
class Customer1{
	private Dept2 dept;
	public Customer1(Dept2 dept) {
		this.dept = dept;
	}
	
	public void consume(final int val){
		new Thread(){
			public void run(){
				dept.consume(val);
			}
		}.start();
	}
}
