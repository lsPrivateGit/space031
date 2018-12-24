package com.ticket.synchronized_test;


/*
 * 当一个线程访问 某对象 的 synchronized方法  或者 synchronized 代码块时，其他线程仍然可以访问该对象的非同步代码块
 * 
 */
public class Demo2 {
	
	public static void main(String[] args) {
		
		final Count count = new Count(); // JDK 1.8中不用加final修饰
		
		//使用匿名内部类方式创建线程 t1
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				count.synchMethod();
				//count.notsynchMethod();
			}
		}, "t1");    // 后面加 一个字符串 “t1”表示的是修改线程的名字 ，默认名字是 Thread-0开始
		
		// 使用匿名内部类方式创建线程 t2
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				count.notsynchMethod();
				//count.synchMethod();
			}
		}, "t2"); // 后面加 一个字符串 “t1”表示的是修改线程的名字 ，默认名字是 Thread-0开始
		
		
		//启动线程
		t1.start();
		t2.start();
		/*结果说明:
		*主线程中新建了两个子线程t1和t2。t1会调用count对象的synMethod()方法，该方法内含有同步块；
		*而t2则会调用count对象的nonSynMethod()方法，该方法不是同步方法。t1运行时，
		虽然调用synchronized(this)获取“count的同步锁”；但是并没有造成t2的阻塞，因为t2没有用到“count”同步锁。
		*/
	}
}

/*
 * 定义一个count类，里面定义两个方法，一个是同步的，一个是非同步的
 */
class Count{
	
	// 同步方法
	public void synchMethod(){
		synchronized (this) {
			try{
			   for( int i =0; i<5;i++){
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName()+" 同步方法synchMethod"+i);
			   }
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 非同步方法
	public void notsynchMethod(){
		try{
			for( int i=0;i<5;i++){
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName()+" 非同步方法notsynchMethod"+i);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}