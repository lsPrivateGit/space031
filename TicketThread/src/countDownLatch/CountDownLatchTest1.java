package countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 示例
 * countDownLatch:是一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
 * countdownlatch:作用是允许1或N个线程等待其他线程完全执行
 */
public class CountDownLatchTest1 {
	
	private static int LATCH_SIZE = 5;
	private static CountDownLatch doneSingal;
	
	/**
	 * 下面通过CountDownLatch实现：
	 * 	"主线程"等待"5个子线程"全部都完成"指定的工作(休眠1000ms)"之后，再继续运行。
	 */
	public static void main(String[] args) {
		try{
			doneSingal = new CountDownLatch(LATCH_SIZE);
			
			//新建5个任务
			for(int i=0;i<LATCH_SIZE;i++)
				new InnerThread().start();
			
			 System.out.println("main await begin.");
			 // "主线程"等待线程池中5个任务的完成
			 doneSingal.await();
			 
			 System.out.println("main await finished.");
			 
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static class InnerThread extends Thread{
		public void run(){
			try{
				Thread.sleep(1000);
				 System.out.println(Thread.currentThread().getName() + " sleep 1000ms.");
				 
				 // 将CountDownLatch的数值减1
				 doneSingal.countDown();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
