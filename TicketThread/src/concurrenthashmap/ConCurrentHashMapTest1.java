package concurrenthashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;

/*
 * ConcurrentHashMap是线程安全的哈希表，而HashMap是非线程安全的
 */
public class ConCurrentHashMapTest1 {
	
	//TODO 当map集合时 HashMap时，会报错；当是 ConCurrentHashMap时，是线程安全的
	//private static Map<String, String> map  = new HashMap<>();
	private static Map<String, String> map = new ConcurrentHashMap<>();
	
	public static void main(String[] args) {
		
		//同时启动两个线程对map进行操作
		new MyThread("t1").start();
		new MyThread("t2").start();
	}
	
	public static void printAll(){
		String key,value;
		// 通过 entrySet遍历 map 集合效率最高，返回的是 key 和 value 的集合
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String, String> entry = iterator.next();
			key = entry.getKey();
			value = entry.getValue();
			 System.out.print(key+" - "+value+", ");
		}
		System.out.println();//换行
	}
	
	private static class MyThread extends Thread{
		
		public MyThread(String name) {
			super(name);
		}
		@Override
		public void run() {
			 int i = 0;
			 while(i++<6){
				// “线程名” + "-" + "序号"
				String val = Thread.currentThread().getName()+i;
				map.put(String.valueOf(i), val);
				//通过printAll()遍历
				printAll();
			 }
		}
	}
}
