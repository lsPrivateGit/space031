package com.md5;

public class Md5Test {
	
	public static void main(String[] args) {
		MD5 md5 = new MD5();
		String md5ofStr = md5.getMD5ofStr("abc123456").toUpperCase();
		System.out.println(md5ofStr);
		//System.out.println("827ccb0eea8a706c4c34a16891f84e7b".toUpperCase());
		//System.out.println(md5.getMD5ofStr("D:\\apache-tomcat-6.0.29.zip"));
		
		String str1 = "2018/06/08 23:00:00";
		String str2 = "2018/06/11 19:54:04";
		System.out.println(str1.compareTo(str2));
		System.out.println(str2.substring(0, 10));
	}
}
