package com.ticket.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Reerantlock和Condition 结合使用示例
 */
public class LockTest3 {
	public static void main(String[] args) {
		Dept3 dept = new Dept3(100);
		Producer3 mPro = new Producer3(dept);
		Customer3 mCus = new Customer3(dept);
		
		mPro.produce(60);
		mPro.produce(120);
		mCus.consume(90);
		mCus.consume(150);
		mPro.produce(110);
	}
}
// 仓库
class Dept3{
	
	private int capacity; //仓库的容量
	private int size ;// 仓库的实际容量
	private Lock lock; // 独占锁
	private Condition fullCondition; // 生产条件
	private Condition emptyCondition;// 消费条件
	
	public Dept3(int capacity) {
		this.capacity = capacity;
		this.size =0;
		this.lock = new ReentrantLock();
		this.fullCondition = lock.newCondition(); // 返回用来与此 Lock 实例一起使用的 Condition 实例。
		this.emptyCondition = lock.newCondition();
	}
	
	
	// 生产方法
	public void produce(int val){
		lock.lock();
		try{
			// left 表示  想要生产的数量（有可能生产量太多，需要多次生产）
			int left = val;
			while(left>0){
				// 库存已满时，等待 消费者 消费产品 ------->即当实际容量大于仓库容量时，则停止生产，等待消费
				while(size>=capacity)
					fullCondition.await(); // 类似于 Thread中的wait()方法
					// 获取 实际生产的数量 （即库存中新增的数量）
					// 如果 库存+需要生产的数量>总的容量，则 实际增量=总的容量-当前容量（此时填满仓库）
					// 否则 实际增量=想要生产的数量
					int inc =(size+left)>capacity?(capacity-size):left;
					size +=inc;
					left -=inc;
					System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",   Thread.currentThread().getName(), val, left, inc, size);
					
					//通知 消费者  可以消费了
					emptyCondition.signal(); // 类似于 Thread中的 notifAll()方法
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
	}
	
	
	//消费方法
	public void consume(int val){
		lock.lock();
		try{
			// left 表示 "客户要消费的数量"(有可能消费量太大，库存不够，需要多次消费)
			int left = val;
			while(left>0){
				// 库存为0时，等待 "生产者" 生产产品
				while(size<=0)
					emptyCondition.await();
					//获取  "实际消费的数量"（即库存中实际减少的数量）
					//如果 "库存"<"客户要消费的数量"，则" 实际消费量"="库存"
					//否则，"实际消费量" = "客户要消费的数量"
					int dec =(size<left)?size:left;
					size -=dec;
					left -=dec;
					System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, dec, size);
					 
					//通知 生产者  可以生产了
					 fullCondition.signal();
			}
		}catch (InterruptedException e) {
		}finally {
			lock.unlock();
		}
	}
	
}

// 生产者
class Producer3{
	
	private Dept3 dept;
	public Producer3(Dept3 dept) {
		this.dept = dept;
	}
	// 生产产品：新建一个线程从仓库中生产产品。
	public void produce(final int val){
		new Thread(){
			public void run(){
				dept.produce(val);
			}
		}.start();
	}
}
// 消费者
class Customer3{
	
	private Dept3 dept;
	public Customer3(Dept3 dept) {
		this.dept = dept;
	}
	// 消费产品：新建一个线程向仓库中生产产品。
	public void consume(final int val){
		new Thread(){
			public void run(){
				dept.consume(val);
			}
		}.start();
	}
	
}




