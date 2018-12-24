package reentrantReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * ReentrantReadWriteLock 读写锁示例
 */
public class ReadWriteLockTest1 {
	public static void main(String[] args) {
		// 创建账户
		MyCount myCount = new MyCount("4238920615242830", 10000); 
		// 创建用户，并指定账户
		User user = new User("Tommy", myCount); 
		
		 // 分别启动3个“读取账户金钱”的线程 和 3个“设置账户金钱”的线程
		for(int i =0;i<3;i++){
			user.getCash();
			user.setCash((i+1)*1000);
		}
	}
	
}

// 用户
class User{
	private String name;            //用户名 
	private MyCount myCount;        //所要操作的账户 
	private ReadWriteLock myLock;   //执行操作所需的锁对象 
	
	public User(String name, MyCount myCount) {
		this.name = name; 
		this.myCount = myCount; 
		this.myLock = new ReentrantReadWriteLock();
	}
	
	// 获取账户钱
	public void getCash(){
		
		// 开启一个线程 获取账户中的金额
		new Thread(){
			public void run(){
				myLock.readLock().lock();
				try{
					 System.out.println(Thread.currentThread().getName() +" getCash start"); 
					 myCount.getCash();
					 Thread.sleep(1000);
					 System.out.println(Thread.currentThread().getName() +" getCash end"); 
				}catch (InterruptedException e) {
					
				}finally{
					myLock.readLock().unlock();
				}
				
			}
		}.start();
	}
	
	// 给账户设置钱
	public void setCash(final int cash) {
		
		// 开启一个线程 设置账户中的金额
		new Thread(){
			public void run(){
				 myLock.writeLock().lock(); 
				 try{
					 System.out.println(Thread.currentThread().getName() +" setCash start"); 
					 myCount.setCash(cash);
					 Thread.sleep(1000);
					 System.out.println(Thread.currentThread().getName() +" setCash end"); 
				 }catch (InterruptedException e) {
				}finally{
					myLock.writeLock().unlock();
				}
			}
		}.start();
	}
	
}

// 账户
class MyCount{
	private String id;         //账号 
	private int    cash;       //账户余额 
	
	MyCount(String id, int cash) {   // 构造函数，初始化
	  this.id = id; 
	  this.cash = cash; 
	} 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	 public int getCash() { 
		 System.out.println(Thread.currentThread().getName() +" getCash cash="+ cash); 
		 return cash;
	 }
	 
	 public void setCash(int cash) {
		System.out.println(Thread.currentThread().getName() +" setCash cash="+ cash); 
		this.cash = cash;
	}
	
	
}