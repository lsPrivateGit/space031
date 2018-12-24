package com.ticket.synchronized_test.yieid;

/*
 * 线程 让步  yield ：作用：让步；
 * 	让当前线程由运行状态进入到就绪状态，从而让其他具有同样优先级的等待线程获取执行权；
 * 但是，并不能保证在当前线程调用yield()之后，其他具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到
 * “运行 状态” 继续运行了；
 */
public class ThreadYieId {
	public static void main(String[] args) {
		ThreadC t1 = new ThreadC("t1");
		ThreadC t2 = new ThreadC("t2");
		t1.start();
		t2.start();
	}
}

class ThreadC extends Thread{
	
	public ThreadC(String name) {    //该构造方法作用是给线程命名
		super(name);
	}
	@Override
	public synchronized  void run() {
		for(int i =0;i<10;i++){
			try {
				Thread.sleep(1000);
				// this.getPriority()：获取当前线程的优先级
				 System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i); 
				 // i整除4时，调用yield
				 if(i%4==0){
					 Thread.yield();
				 }
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}