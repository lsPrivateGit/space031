package cn.sun.crm.utils;

import org.springframework.util.DigestUtils;

public class Md5Utils {
	
	
	/**
	 * @desecirption :循环加密10次
	 * @param pwd
	 * @return
	 */
	public static String getMd5Pwd(String pwd){
		
		for(int i=0;i<10;i++){
			pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
		}
		return pwd;
	}
	
	//827ccb0eea8a706c4c34a16891f84e7b
	public static void main(String[] args) {
		System.out.println(Md5Utils.getMd5Pwd("12345"));
	}
	
	
}
