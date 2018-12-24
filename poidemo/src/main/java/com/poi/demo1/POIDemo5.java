package com.poi.demo1;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 删除sheet页
 * @author ex-sunjiamin
 *
 */
public class POIDemo5 {
	
	public static void main(String[] args) {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建三个sheet 页
		workbook.createSheet(); //sheet0
		workbook.createSheet(); //sheet1
		workbook.createSheet(); //sheet2
		
		workbook.removeSheetAt(workbook.getSheetIndex("Sheet1")); //移除sheet1页
		
		FileOutputStream out =null;
		
		try{
			/**
			 * sheet页码下标从0开始的.......
			 */
			System.out.println("Sheet0:"+workbook.getSheetIndex("Sheet0"));
			System.out.println("Sheet2:"+workbook.getSheetIndex("Sheet2"));
			
			out = new FileOutputStream("test2.xls");
			workbook.write(out);
			System.out.println("删除.......");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
