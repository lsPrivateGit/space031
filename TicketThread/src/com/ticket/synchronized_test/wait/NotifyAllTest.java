package com.ticket.synchronized_test.wait;


/*
 * notifyAll()的用法；它的作用是唤醒在此对象监视器上等待的所有线程。
 */
public class NotifyAllTest {

	private static Object obj = new Object();
	
	public static void main(String[] args) {
		
		ThreadC t1 = new ThreadC("t1");
		ThreadC t2 = new ThreadC("t2");
		ThreadC t3 = new ThreadC("t3");
		
		// 1、启动三个线程  ，然后会执行 obj.wait()方法，使得这些线程进入等待状态，并释放锁，此时主线程main获取锁
		t1.start();
		t2.start();
		t3.start();
	
		try{
			System.out.println(Thread.currentThread().getName()+" sleep(3000)");
			Thread.sleep(3000);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//2、主线程获取obj的同步锁之后，通过obj唤醒ThreadC的线程
		synchronized(obj){
				System.out.println(Thread.currentThread().getName()+" notifyAll");
				
				//唤醒所有线程 -----------------主线程获取到了obj的线程锁，然后唤醒 所有在等待的线程
				obj.notifyAll();
			}
		}
		
	

  static class ThreadC extends Thread{
	
	   public ThreadC(String name) {
		   super(name);
	    }
	   
		@Override
		public void run() {
			   
			 synchronized(obj){  
			  try {
				  
				  System.out.println(Thread.currentThread().getName()+" wait ");
				  // 让当前线程的进入等待状态
				  obj.wait();      // obj.wait() 会让当前 ThreadC 的线程进入等待状态 ，并释放锁给主线程
				  System.out.println(Thread.currentThread().getName() + " continue");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }	  
		}
	   
	   
  }
}