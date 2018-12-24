package com.ticket.synchronized_test.interrupt;

public class ThreadInterrupt03 {
	public static void main(String[] args) {
		try {
			ThreadC t1  = new ThreadC("t1");   // 创建线程 ThreadC t1
			System.out.println(t1.getName() +" ("+t1.getState()+") is new.");  
			
			
			t1.start();                      // 启动“线程t1” -----------这里是在主线程中启动线程  t1
			System.out.println(t1.getName() +" ("+t1.getState()+") is started.");  
			
			// 主线程休眠300ms，然后主线程给t1发“中断”指令。
			Thread.sleep(300);        // 这里是让 main 线程休眠 300毫秒
			t1.stopTask();	
			System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted.");
			
			
			// 主线程休眠300ms，然后查看t1的状态。
			Thread.sleep(300);
			System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted now.");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
}

class ThreadC extends Thread{
	private volatile boolean flag = true; // volatile 关键字作用是使得更改立即完成(多线程环境下，更改某个值会存在寄存器中一份，使得更改不一定能及时获取)
	
	public void stopTask(){
		flag = false;
	}
	
	public ThreadC(String name) {
		super(name);
	}
	@Override
	public void run() {
		synchronized(this){
			try {
				int i = 0;
				while(flag){
				  Thread.sleep(100);	
				  i++;
				  System.out.println(Thread.currentThread().getName()+" ("+this.getState()+") loop " + i);  
				}
			} catch (InterruptedException e) {
				 System.out.println(Thread.currentThread().getName() +" ("+this.getState()+") catch InterruptedException."); 
			}
		}
	}
	
}