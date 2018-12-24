package com.ticket.synchronized_test.priority;
/*
 * 守护线程 ：
 */
// 运行结果说明：
// (01) 主线程main是用户线程，它创建的子线程 t1也是用户线程
// (02) t2是守护线程。在“主线程main"和“子线程t1"（它们都是用户线程）执行完毕后，只剩下t2这个守护线程的时候，JVM自动退出，则程序不会再次执行打印输出t2中的值了


public class ThreadDaemon {
	
	public static void main(String[] args) {
		 System.out.println(Thread.currentThread().getName()
				 +"(isDaemon="+Thread.currentThread().isDaemon()+ ")");
		 
		 Thread t1 = new ThreadB("t1");
		 Thread t2 = new MyDaemon("t2");
		 t2.setDaemon(true);  // 设置t2为守护线程  ----------设置守护线程必须在start()方法前设置，否则会报IllegalThreadStateExceptiont2异常
		 t1.start();
		 t2.start();
	}
}

// 创建一个普通线程
class ThreadB extends Thread{
	
	public ThreadB(String name) {
		super(name);
	}
	@Override
	public void run() {
		try{
			for(int i =0;i<5;i++){
				Thread.sleep(3);
				System.out.println(this.getName() +"(isDaemon="+this.isDaemon()+ ")" +"(priority="+Thread.currentThread().getPriority()+ ")"+", loop "+i);
			}
		}catch (InterruptedException  e) {
			e.printStackTrace();
		}
	}
}


class MyDaemon extends Thread{
	
	public MyDaemon(String name) {
		super(name);
	}
	@Override
	public void run() {
		try{
			for(int i =0;i<1000;i++){
				Thread.sleep(1);
				System.out.println(this.getName() +"(isDaemon="+this.isDaemon()+ ")" +"(priority="+Thread.currentThread().getPriority()+ ")" +", loop "+i);
			}
		}catch (InterruptedException  e) {
			e.printStackTrace();
		}
	}
	
}

