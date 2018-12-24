package com.poi.demo1;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 改变sheet 页的名称
 * @author ex-sunjiamin
 *
 */
public class POIDemo6 {
	
	public static void main(String[] args) {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		workbook.createSheet();//创建sheet0 页
		workbook.createSheet(); //创建sheet1页
		
		
		workbook.setSheetName(0, "test");
		workbook.setSheetName(1, "测试中文sheet");
		
		FileOutputStream out =null;
		
		try{
			out = new FileOutputStream("test3.xls");
			workbook.write(out);
			System.out.println("操作完毕");
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
