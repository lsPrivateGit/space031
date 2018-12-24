package com.poi.demo1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/*
 * 创建sheet  
 */
public class POIDemo3 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		workbook.createSheet();//创建sheet 页 ，默认从0开始
		workbook.createSheet();
		workbook.createSheet("test");
		
		FileOutputStream out = new FileOutputStream("test.xls");
		
		try {
			workbook.write(out);
			
			System.out.println("创建完毕");
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
