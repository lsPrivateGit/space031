package com.ticket.synchronized_test.syn01;

/*
 * 使用多线程  依次输出   sunjiamn  和  wangdachui
 */
public class TraditionalThreadSafe {
	
	public static void main(String[] args) {
		
		new TraditionalThreadSafe().init();
		
	}
	
	private  void init() {
		/*
		 * 内部类不能直接在static方法中创建
		 */
		 Outputer out1 = new Outputer(); // 两个线程同时使用同一个Outputer对象，以此确保它们是同一把锁
		
		 Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
			  while(true){	
				try{
					Thread.sleep(500);
					out1.printer("sunjiamn");
				}catch (Exception e) {
					e.printStackTrace();
				}
			 }
			}	  
		},"t1" );
		
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
			 while(true){	
				try{
					Thread.sleep(500);
					out1.printer("wangdachui");
				}catch (Exception e) {
					e.printStackTrace();
				}
			 }
			}
		},"t2");
		
		
		t1.start();
		t2.start();
	
		
	}

	/**
	 * Outputer 类说明：
	 * 通过传入一个String字符串，然后将其转换为单个 char 字符，在输出
	 */
	 class   Outputer {
		 
		public void printer(String name){
			
			/*
			 * 如果这里不加上同步锁，则会打印输出的时候，出错，前面还没打印完，后面就接着打印；
			 */
		  synchronized(this){	 
			int length = name.length();
			for(int i = 0 ;i<length;i++){
				char ch = name.charAt(i);
				System.out.print(ch);
			}
			System.out.println(); // 换行
		}
	  }
	}
	
	
}
