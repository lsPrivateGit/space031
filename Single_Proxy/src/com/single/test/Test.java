package com.single.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		Hello hello = new Hello();
		hello.setA(100);
		ProxyDemo proxy = new ProxyDemo(hello);
		String a = proxy.sayHello(hello);
		System.out.println(a);
		System.out.println("执行完毕。。。。");
		
		//map 
		String [] messages ={"#name#","#age#"};
	        Map<String,String> map = new HashMap<>();
	        map.put("name","张三");
	        map.put("age","13");
	      
	        map.forEach((key,value)->{
	        	for(int i = 0;i < messages.length;i++){
	        		 if(messages[i].contains(key)){
	 	            	messages[i]=messages[i].replaceAll("#"+key+"#",value);
	 	            }
	        	}
	           
	        });
	        /*System.out.println(messages[0]);
	        System.out.println(messages[1]);*/
	        List<String> stringB = Arrays.asList(messages);
	        System.out.println(stringB.toString());
	}
}
