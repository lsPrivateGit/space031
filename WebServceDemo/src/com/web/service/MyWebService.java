package com.web.service;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
/**
 * 自定义WebSerivce服务
 * @author ex-sunjiamin
 *
 */
@WebService
public class MyWebService {
	
	//服务提供的方法
	public String sayHi(String name){
		System.out.println("这个方法被调用了！");
		return "hi"+name;
	}
	public static void main(String[] args) {
		String address = "http://localhost:9999/hi"; //发布的地址
		Object implementor = new MyWebService();//指定提供服务的对象
		Endpoint.publish(address, implementor);//发布服务
	}
}
