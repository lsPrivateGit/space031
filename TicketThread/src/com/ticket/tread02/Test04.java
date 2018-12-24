package com.ticket.tread02;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * 采用AtomicInteger：
 */
public class Test04 {
	public AtomicInteger inc  = new AtomicInteger();
	public  void increase() {
        inc.getAndIncrement();
    }
	
	public static void main(String[] args) {
        final Test04 test = new Test04();
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
