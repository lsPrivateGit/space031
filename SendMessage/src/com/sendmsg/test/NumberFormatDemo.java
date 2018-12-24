package com.sendmsg.test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/*
 * NumberFormat格式化数字
 */
public class NumberFormatDemo {
		
	public static void main(String[] args) {
		NumberFormatDemo.formatFun("###,###.###",111222.34567) ;  
		NumberFormatDemo.formatFun("000,000.000",11222.34567) ;  
		NumberFormatDemo.formatFun("###,###.###￥",111222.34567) ;  
		NumberFormatDemo.formatFun("000,000.000￥",11222.34567) ;  
		NumberFormatDemo.formatFun("##.###%",0.345678) ;  
		NumberFormatDemo.formatFun("00.###%",0.0345678) ;  
		NumberFormatDemo.formatFun("###.###\u2030",0.345678) ; 
	}
	
	
	public static void formatFun(String patten ,double value){
		//NumberFormat的子类
		DecimalFormat df=null;
		 //NumberFormat format = DecimalFormat.getInstance();
		 df=new DecimalFormat(patten);
		 String str = df.format(value);
		 System.out.println("使用" + patten  + "格式化数字" + value + "：" + str);
		 
	}
}

