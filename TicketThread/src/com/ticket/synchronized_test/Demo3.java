package com.ticket.synchronized_test;

/*
 * 当一个线程访问 某对象 的synchronized方法 或者 synchronized 代码块时，其他线程 对该对象的其他 synchronized 或者
 * synchronized代码块的访问将被阻塞
 */
public class Demo3 {
	
	public static void main(String[] args) {
		final Count2 c = new Count2();
		
		Thread t1  = new Thread(new Runnable() {
			
			@Override
			public void run() {
				c.synchMethod();
			}
		}, "t1");
		
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				c.synchMethod2();
			}
		}, "t2");
		
		//启动线程
		t1.start();
		t2.start();
		
		//说明：通过查看运行结果，可以得到，线程t2需要等到线程 t1执行完它的方法后（即释放当前对象的同步锁），t2才能执行；
		
	}
}

class Count2{
	
	// 定义同步方法1
	public void synchMethod(){
		synchronized (this) {
			try{
				for(int i =0;i<5;i++){
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName()+" 同步方法1 "+i);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//定义同步方法2 
	public void synchMethod2(){
		synchronized(this){
			try{
				for(int i =0 ;i<5;i++){
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName()+ " 同步方法2 "+i);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}