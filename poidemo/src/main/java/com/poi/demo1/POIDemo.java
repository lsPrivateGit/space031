package com.poi.demo1;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POIDemo {
	public static void main(String[] args) {
		
		HSSFWorkbook workbook = new HSSFWorkbook();//创建一个空白的的workbook
		
		FileOutputStream out =null;
		
		try{
			out = new FileOutputStream("sample.xls");
			workbook.write(out);//调用HSSFWorkbook类的write方法写入输出流
			
			System.out.println("生成了一个excel表格");
			
		}catch (IOException  e) {
			System.out.println(e.toString());
			
		}finally{
			try{
				out.close();
			}catch (IOException e) {
				System.out.println(e.toString());
			}
		}
		
		
	}
}
