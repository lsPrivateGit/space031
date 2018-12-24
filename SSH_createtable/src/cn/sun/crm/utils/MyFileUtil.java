package cn.sun.crm.utils;

import java.util.UUID;

public class MyFileUtil {

	/**
	 * 获取uuid的文件名字
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName){
		//icon_01.jpg --->lkaldsjflajslfdjalsjdflajsdf.jpg
		
		String suffix = fileName.substring(fileName.lastIndexOf(".")); //获取.jpg名字
		
		String prefix = UUID.randomUUID().toString().replaceAll("-", "");
		
		return prefix + suffix;
	}
	
	public static void main(String[] args) {
		String filename="aaa.jgp";
		System.out.println(filename);
		System.out.println("filename长度是："+filename.length());
		System.out.println("filename最后出现的.是在那个位置："+filename.lastIndexOf("."));
		System.out.println("截取的字符串是："+filename.substring(filename.lastIndexOf(".")));
		
		System.out.println(MyFileUtil.getFileName(filename));
		
	}
	
	
	
}
