package com.springmvc.utils;

/**
 * 自定义异常
 * @author ex-sunjiamin
 *
 */
public class CustomException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String message; //异常信息
	
	public CustomException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
