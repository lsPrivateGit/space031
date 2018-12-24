package copyonwritearrayset;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/*
 * copyonWriteArraySet 示例：
 * copyonWriteArraySet是通过copyonWriteArrayList来实现的，且它里面的方法基本都是通过copyonWriteArrayList实现
 * CopyOnWriteArraySetTest1
 */
public class CopyOnWriteArraySetTest1 {
	
	//private static Set<String> set = new HashSet<>(); // 如果是HashSet，则会报错
	private static Set<String> set = new CopyOnWriteArraySet<>(); // 如果是CopyOnWriteArraySet，则正常，这个是线程安全的类
	public static void main(String[] args) {
		
		 // 同时启动两个线程对set进行操作！
        new MyThread("ta").start();
        new MyThread("tb").start();
	}
	
	public static class MyThread extends Thread{
		public MyThread(String name) {
			super(name);
		}
		@Override
		public void run() {
			int i = 0;
			while(i++ < 10){
				 // “线程名” + "-" + "序号"
				String val = Thread.currentThread().getName() + "-" + (i%6);
				set.add(val);
				  // 通过“Iterator”遍历set。
				 printAll();
			}
		}
	}

	public static void printAll() {
		String value = null;
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			value = iterator.next();
			System.out.print(value+", ");
		}
		 System.out.println();
	}
	
}
