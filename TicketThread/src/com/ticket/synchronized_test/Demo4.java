package com.ticket.synchronized_test;

/*
 * “synchronized方法”是用synchronized修饰方法，
 *  而 “synchronized代码块”则是用synchronized修饰代码块。
 *  synchronized代码块中的this是指当前对象。也可以将this替换成其他对象，
 *      例如将this替换成obj，则foo2()在执行synchronized(obj)时就获取的是obj的同步锁。
 *      
 */
public class Demo4 {
	/*
	 * synchronized代码块可以更精确的控制冲突限制访问区域，有时候表现更高效率。下面通过一个示例来演示：
	 */
	
	public static void main(String[] args) {
		Demo4 d = new Demo4();
		
		long start ,end;
		
		start = System.currentTimeMillis();
		d.synchMethod();
		end = System.currentTimeMillis() - start;
		System.out.println("调用同步方法耗时："+end);
		
		start = System.currentTimeMillis();
		d.synchBlockMethod();
		end = System.currentTimeMillis() - start;
		System.out.println("调用同步代码块方法耗时："+end);
	}
	
	
	/*
	 * 同步方法 @@@  使用synchronized修饰方法
	 */
	public synchronized void synchMethod(){
		for(long i =0;i<100000000;i++)
			;
	}
	
	
	/*
	 * 同步代码块： 在方法中用synchronized(this)修饰
	 */
	public void synchBlockMethod(){
		synchronized(this){
			for(long i =0;i<100000000;i++)
				;
		}
	}
	
}
