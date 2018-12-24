package com.poi.demo1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class POIDemo2 {
	
	public static void main(String[] args) {
		
		FileInputStream in = null;
		
		HSSFWorkbook workbook = null;
		try{
			
			//将excel文件转为输入流
			in = new FileInputStream("sample1.xls");
			//构建POIFSFileSystem类对象，用输入流作为参数传入
			POIFSFileSystem fs = new POIFSFileSystem(in);
			//创建workbook对象，用POIFSFileSystem作为对象传入
			workbook = new HSSFWorkbook(fs);
		}catch (IOException  e) {
			e.printStackTrace();
		}finally {
			try{
		        in.close();
		      }catch (IOException e){
		        System.out.println(e.toString());
		      }
		}
		
		FileOutputStream  out = null;
		try{
			out = new FileOutputStream("sample2.xls");
			workbook.write(out);
			
			System.out.println("写入完成！！！");
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
		      try {
		          out.close();
		        }catch(IOException e){
		          System.out.println(e.toString());
		        }
		      }
	}
}
