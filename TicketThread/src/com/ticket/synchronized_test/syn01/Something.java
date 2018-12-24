package com.ticket.synchronized_test.syn01;

/*
 * 实例锁：锁在某一个实例对象上，如果该类是单例，那么该所也具有全局锁的概念。实例锁对应的就是synchronized关键字
 * 全局锁：该锁针对的是类，无论实例多少个对象，那么线程都共享该锁。
 * 		 全局锁对应的就是 static synchronized（或者是锁在该类的class或者classloader对象上）
 * 
 */
public class Something {
	/*	假设，Something有两个实例x和y。分析下面4组表达式获取的锁的情况。
	 *  (01) x.isSyncA()与x.isSyncB()   ==== 不能被同时访问。因为isSyncA()和isSyncB()都是访问同一个对象(对象x)的同步锁！
	 *	(02) x.isSyncA()与y.isSyncA()   ==== 可以同时被访问。因为访问的不是同一个对象的同步锁，x.isSyncA()访问的是x的同步锁，而y.isSyncA()访问的是y的同步锁。
	 *	(03) x.cSyncA()与y.cSyncB() ==== 不能被同时访问。因为cSyncA()和cSyncB()都是static类型，x.cSyncA()相当于Something.isSyncA()，y.cSyncB()相当于Something.isSyncB()，因此它们共用一个同步锁，不能被同时访问问。
	 *	(04) x.isSyncA()与Something.cSyncA() ===可以被同时访问。因为isSyncA()是实例方法，x.isSyncA()使用的是对象x的锁；而cSyncA()是静态方法，Something.cSyncA()可以理解对使用的是“类的锁”。因此，它们是可以被同时访问的。
	 */
	
	 //实例锁
	public synchronized void isSyncA(){
		try{
			for(int i =0;i<5;i++){
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName()+" isSyncA() :"+i);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}  
	 //实例锁
	public synchronized void isSyncB(){
		try{
			for(int i =0;i<5;i++){
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName()+" isSyncA() :"+i);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	//全局锁
	public static synchronized void cSyncA(){
		try{
			for(int i =0;i<5;i++){
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName()+" cSyncA() :"+i);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	//全局锁
	public static synchronized void sSyncB(){
		try{
			for(int i =0;i<5;i++){
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName()+" sSyncB() :"+i);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
