package com.ticket.tread02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 采用Lock
 */
public class Test03 {
	public int inc = 0;
	Lock lock = new ReentrantLock();
	
	public void increase(){
		lock.lock();
		try{
			inc++;
		}finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
	    // System.out.println("当前活动的线程数："+Thread.activeCount());
        final Test03 test = new Test03();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }
 
        System.out.println("当前活动的线程数："+Thread.activeCount());
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}
