package condition;

/*
 * Condition示例
 * 示例1是通过Object的wait(), notify()来演示线程的休眠/唤醒功能。
 */
public class WaitTest1 {
	
	public static void main(String[] args) {
		ThreadA t1 = new ThreadA("t1");
		
		// 通过synchronized(t1)获取“对象t1的同步锁”
		synchronized (t1) {
			try{
				System.out.println(Thread.currentThread().getName()+" start t1"); // 第一步
				t1.start();
				
				System.out.println(Thread.currentThread().getName()+" block");// 第二步
                t1.wait();    // 等待  注意： 这里  t1.wait()是让当前线程等待，并释放对象的锁，即让main线程等待
                
                System.out.println(Thread.currentThread().getName()+" continue");// 第四步
                
			}catch (InterruptedException e) {
				 e.printStackTrace();
			}
		}
	}
	
	static class ThreadA extends Thread{
		public ThreadA(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			// 给当前对象加上同步锁
			synchronized(this){
			  System.out.println(Thread.currentThread().getName()+" wakup others");// 第三步
			  notify();    // 唤醒“当前对象上的等待线程”               -----------这里通过 t1来唤醒 main线程
			}
		}
	}
}

