package com.transient_demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * transient 案例测试
 * 
 * 被transient 修饰的变量不能被序列化到磁盘
 */
public class TransientTest {
	
	
	public static void main(String[] args)  {
		
		User user = new User();
		user.setUname("test");
		user.setPwd("123456");
		
		System.out.println("read before Serializable：");
		System.out.println("uname:"+user.getUname());
		System.out.println("pwd:"+user.getPwd());
		
		/*
		 * 使用流将 user写入到test.txt文件中
		 */
		try {
			//定义一个 ObjectOutStream 输出流
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test.txt"));
			//将user对象写入到text.txt文件中
			out.writeObject(user);
			//out.writeUTF("UTF-8");
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/**
		 * 使用流从 test.txt中读取内容
		 */
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("test.txt"));
			user = (User) in.readObject();
			in.close();
			
			System.out.println("read after Serializable：");
			System.out.println("uname:"+user.getUname());
			System.out.println("pwd:"+user.getPwd());	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
