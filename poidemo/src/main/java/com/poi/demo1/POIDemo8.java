package com.poi.demo1;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/*
 * 设置列默认长度
 */
public class POIDemo8 {
	
	public static void main(String[] args) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		HSSFSheet sheet = workbook.createSheet();
		
		sheet.setDefaultColumnWidth((short)5);//将默认的列宽设为5个文字大小
		
		
		FileOutputStream  out =null;
		
		try {
			out  = new FileOutputStream("test5.xls");
			workbook.write(out);
			System.out.println("操作完成！");
		} catch (Exception e) {
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
