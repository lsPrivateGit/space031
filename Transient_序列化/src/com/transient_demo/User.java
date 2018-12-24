package com.transient_demo;

import java.io.Serializable;

/**
 * 定义一个 User 类，包含 uname  pwd两个属性
 * 其中 pwd 用 transient 修饰，则不会序列化到磁盘
 */
public class User implements Serializable {
	
	private String uname;
	private transient String  pwd;   //使用transient 修饰变量，则该变量不会被持久化到磁盘中
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}	
