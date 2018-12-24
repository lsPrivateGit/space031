package com.poi.demo1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/*
 * 复制sheet
 */
public class POIDemo4 {
	
	public static void main(String[] args) {
		
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		
		try{
			in  =  new FileInputStream("test.xls"); //把test.xls当做输入流
			
			POIFSFileSystem fs = new POIFSFileSystem(in); //创建POIFSFileSystem 实例对象
			
			workbook = new HSSFWorkbook(fs); 
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		workbook.cloneSheet(0);//复制sheet
		workbook.cloneSheet(1);//复制第二个sheet
		
		
		FileOutputStream out =null;
		
		try{
			out = new FileOutputStream("test.xls");
			workbook.write(out);
			
			System.out.println("复制完毕！");
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
