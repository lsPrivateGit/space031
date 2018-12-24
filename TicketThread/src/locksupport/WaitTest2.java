package locksupport;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * 使用LockSupport 来阻塞线程 park() 和 解除 阻塞线程 unpark()
 * 
 * 说明：part 和 wait的区别：wait让线程阻塞前，必须通过synchronized获取同步锁，而 part 不用
 */
public class WaitTest2 {
	
	private static Thread mainThread;
//	ReentrantReadWriteLock.ReadLock readLock;
//	ReentrantReadWriteLock.WriteLock writeLock;
	public static void main(String[] args) {
		
		 ThreadA ta = new ThreadA("ta");
		//获取主线程
		mainThread = Thread.currentThread();
		
		System.out.println(Thread.currentThread().getName()+" start ta");
		ta.start();
		
	    System.out.println(Thread.currentThread().getName()+" block");
	    
	    LockSupport.park(mainThread);//主线程阻塞
	    
	    System.out.println(Thread.currentThread().getName()+" continue");

	}
	static class ThreadA extends Thread{
		public ThreadA(String name) {
			super(name);
		}
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+" wakup others");
			// 唤醒主线程
			LockSupport.unpark(mainThread);
		}
		
	}
}
