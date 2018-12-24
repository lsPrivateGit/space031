package com.springmvc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;




/*
 * 自定义日期转换类
 */
public class DateConverter implements Converter<String,Date> {
	
	//date类型和POJO中的date类型一致。
	@Override
	public Date convert(String source) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
		
		try{
			return simpleDateFormat.parse(source);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	

}
