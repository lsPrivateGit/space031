package com.threadpool_线程池1;

public class TestThread1 {
	public static void main(String[] args) {
		//Runner1 r = new Runner1();
		//r.run();
		//new Thread(r).start();
		
		Runner2 r = new Runner2();
		r.start();
		for(int i=0;i<10;i++){
			            System.out.println("maintheod："+i);
		}
		
	}
	
}
class Runner1 implements Runnable{
     public void run(){
         for(int i=0;i<10;i++){
             System.out.println("Runner1："+i);
         }
     }
 }
class Runner2 extends Thread{
     public void run(){//重写run()方法的实现
         for(int i=0;i<=10;i++){
             System.out.println("Runner2："+i);
         }
     }
 }