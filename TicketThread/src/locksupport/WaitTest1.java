package locksupport;

public class WaitTest1 {
	
	public static void main(String[] args) {
		ThreadA t1 = new ThreadA("t1");
		
		synchronized(t1){ // 通过synchronized(t1) 来获取对象 t1的同步锁
			try {
				System.out.println(Thread.currentThread().getName()+"  start t1");
				t1.start();
				
				System.out.println(Thread.currentThread().getName()+" block");
				t1.wait();
				
				System.out.println(Thread.currentThread().getName()+" countine");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

		
	}
	//定义线程 ThreadA类
	static class ThreadA extends Thread{
		
		public ThreadA(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			synchronized (this) { // 通过 synchronized(this)获取 当前对象的同步锁
				System.out.println(Thread.currentThread().getName()+" wakup others ");
				notify(); // 唤醒“当前对象上的等待线程”
			}
		}
	}
}
