package com.ticket.customerandproduce;

/*
 * 多线程经典问题：生产/消费者模型
 * (1)生产者仅仅在仓储未满时候生产，仓满则停止生产；
 * (2)消费者仅仅在仓储有产品时候才能消费，仓空则等待；
 * (3)当消费者发现仓储没产品可消费时候会通知生产者生产；
 * (4)生产者在生产出可消费产品的时候，应该通知等待的消费者去消费
 */
public class Demo {
	
	public static void main(String[] args) {
		Depot depot = new Depot(100);
		Producer mPro = new Producer(depot);
		Customer mCus = new Customer(depot);
		
		mPro.produce(60); //生产 60
		mPro.produce(120);//生产 120
		mCus.consume(90);//消费 90
		mCus.consume(150);//消费150
		mPro.produce(110);//生产110
		
	}
	
	
	
}

 // 表示 仓库
class Depot{
	private int capacity;// 仓库容量
	private int size ; // 仓库的实际数量
	
	public Depot(int capacity) {
		this.capacity = capacity;
		this.size = 0;
	}
	/*
	 * 生产方法
	 */
	public synchronized void producee(int val){
		try{
			int left = val; // left 表示“想要生产的数量”(有可能生产量太多，需多次生产)
			while(left>0){
				//库存已满时，等待消费者 消费产品
				while(size>=capacity)
					wait();
					// 获取 实际生产的数量  （即库存中新增的数量）
				   // 如果 库存 + 想要生产的数量 > 总的容量，则 实际增量 = 总的容量— 当前容器 （此时填满仓库）
				  // 否则， 实际增量 = 想要生产的数量
				 int inc = (size+left)>capacity ?(capacity-size):left;
				 size += inc;
				 left -= inc;
				 System.out.printf("%s produce(%3d) --> left=%3d, inc=%3d, size=%3d\n",Thread.currentThread().getName(), val, left, inc, size);
				 
				 // 通知消费者可以消费了
				 notifyAll();
				
			}
		}catch (InterruptedException  e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 表示 消费的方法
	 */
	public synchronized void consume(int val){
		try{
			// left 表示 客户要消费的数量（有可能消费量太大了，库存不够 ，需多次消费）
			int left = val;
			while(left>0){
				// 库存为0时，等待 生产者  生产产品
				while(size<=0)
					wait();
					// 获取 实际消费的数量（即库存中实际减少的数量）
				   // 如果 库存 < 客户要消费的数量，则   实际消费量 = 库存
				  // 否则，实际消费量=客户要消费的数量
				int dec = (size<left)?size:left;
				size -= dec;
				left -= dec;
				System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, dec, size);
				
				notifyAll();
				
			}
		}catch (InterruptedException  e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "capacity:"+capacity+", actual size:"+size;
	}
}

// 生产者
class Producer {
	private Depot depot ;
	
	//构造方法中传入 仓库 类，用于初始化数据
	public Producer(Depot depot) {
		this.depot = depot;
	}
	//消费产品：新建一个线程向仓库中生产产品
	public void produce(final int val){
			new Thread(){
				public void run(){
					// 调用仓库对象 生产 产品
					depot.producee(val);
				}
			}.start();
	}
	
}

// 消费者
class Customer{
	
	private Depot depot ;
	public Customer(Depot depot) {
		this.depot = depot;
	}
	
	// 消费产品：新建一个线程 从仓库中消费产品。
	public void consume(final int val){
		new Thread(){
			public void run(){
				depot.consume(val);
			}
		}.start();
	}
	
}






