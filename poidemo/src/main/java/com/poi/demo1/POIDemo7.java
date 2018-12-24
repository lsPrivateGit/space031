package com.poi.demo1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/*
 * 设置列宽度
 */
public class POIDemo7 {
	
	public static void main(String[] args) {
		FileInputStream in =null;
		HSSFWorkbook workbook =null;
		
		try{
			in = new FileInputStream("test4.xls");
			POIFSFileSystem fs = new POIFSFileSystem(in);
			workbook = new HSSFWorkbook(fs);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//获取第一列的sheet属性对象
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		short[] width = new short[3];
		
		for(int i=0;i<3;i++){
		  width[i]=sheet.getColumnWidth((short)i); //每列的宽度
		  System.out.println(i+"列宽度是："+ width[i]);
		}
		//循环获取0,1,2的宽度
		//将第二列的宽度赋给第0,1列
		sheet.setColumnWidth((short)0, width[2]);
		sheet.setColumnWidth((short)1, width[2]);
		
		FileOutputStream out = null;
		
		try{
			out = new FileOutputStream("test4.xls");
			workbook.write(out);
			System.out.println("操作完毕！");
		}catch (Exception e) {
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
