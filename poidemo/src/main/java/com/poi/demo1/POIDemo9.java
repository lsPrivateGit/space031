package com.poi.demo1;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * poi写入中文
 * @author ex-sunjiamin
 *
 */
public class POIDemo9 {
	
	public static void main(String[] args) {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		HSSFSheet sheet = workbook.createSheet();
		
		HSSFRow row1 = sheet.createRow(0);//创建行   0表示第一行
		
		HSSFCell cell1 = row1.createCell(0); //创建单元格  ，
		
		cell1.setCellValue("使用中文");   //在创建的行的第一个单元格中
		
		HSSFCell cell2 = row1.createCell(1); 
		cell2.setCellValue("日本語を使う");
		
		FileOutputStream out =null;
		
		try {
			out = new FileOutputStream("test6.xls");
			workbook.write(out);
			System.out.println("操作完成！");
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
