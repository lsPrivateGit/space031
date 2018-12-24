package com.ticket.synchronized_test.sleep;

/*
 * 线程 sleep ：它是Thread中的方法，作用是让当前线程休眠；
 *  即当前线程会从“运行状态”进入到“休眠（阻塞）状态”；
 *  sleep（）会指定休眠时间，线程休眠时间会大于/等于该休眠时间；在线程重新被唤醒时，它会由“阻塞状态”变成“就绪状态”，
 *  从而等待CPU的调度执行
 */
public class ThreadSleep {
	public static void main(String[] args) {
		ThreadC t1 = new ThreadC("t1");
		t1.start();
	}
}

class ThreadC extends Thread{
	
	public ThreadC(String name) {
		super(name);
	}
	
	@Override
	public synchronized void run() {
		for(int i =0;i<10;i++){
			 System.out.printf("%s:%d\n", this.getName(),  i); 
			  // i能被4整除时，休眠3000毫秒
			 if(i%4==0){
				 try{
					 Thread.sleep(3000);
				 }catch (Exception e) {
					 e.printStackTrace();
				}
			 }
		}
	}
	
}
