package com.ticket.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ReentrantLock 示例
 */
public class LockTest1 {
	public static void main(String[] args) {
		Dept dept = new Dept();
		Producer mPro = new Producer(dept);
		Customer mCus = new Customer(dept);
		
		mPro.produce(60);
		mPro.produce(120);
		mCus.consume(90);
		mCus.consume(150);
		mPro.produce(110);
	}
}
//表示 仓库
class Dept{
	private int size ; // 仓库实际数量
	private Lock lock ;// 定义独占锁（ReentrantLock）
	
	public Dept() {
		this.size = 0;
		this.lock = new ReentrantLock();
	}
	//生产方法
	public void produce(int val){
		lock.lock(); // 锁住
		try{
			size += val;
			System.out.printf("%s produce(%d) --> size=%d\n", Thread.currentThread().getName(), val, size);
		}finally {
			lock.unlock(); //解锁
		}
	}
	// 消费 方法
	public void consume(int val){
		lock.lock();
		try{
			size -= val;
			  System.out.printf("%s consume(%d) <-- size=%d\n", Thread.currentThread().getName(), val, size);
		}finally{
			lock.unlock();
		}
	}
}

// 生产者
class Producer {
	private Dept dept;
	public Producer(Dept dept) {
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


// 消费者
class Customer {
	private Dept dept;
	public Customer(Dept dept) {
		this.dept = dept;
	}
	// 消费产品 ：新建一个线程向仓库中消费产品
	public void consume(final int val){
		new Thread(){
			public void run(){
				dept.consume(val);
			}
		}.start();
	}
}







