package com.single.tt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.single.test.SingleTon03;

/*
 * 反序列化创建新对象
 */
public class TestSingleton2 {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		SingleTon03 s1 = SingleTon03.getInstance();
		System.out.println(s1);
		
		//通过反序列化的方式构造多个对象
		//定义一个ObjectOutputStream：对象输入流，用于将一个对象写到文件中
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("a.txt"));
		out.writeObject(s1);
		out.close();
		System.out.println("写入成功！");
		
		
		//在从文件中读出该对象
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("a.txt"));
		SingleTon03 s2= (SingleTon03) in.readObject();
		in.close();
		System.out.println(s2);
		System.out.println("读取成功");
		System.out.println(s1 == s2);
	}
}
