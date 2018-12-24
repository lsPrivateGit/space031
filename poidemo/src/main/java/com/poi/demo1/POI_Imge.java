package com.poi.demo1;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/*
 * poi中插入图片
 */
public class POI_Imge {
	
	public static void main(String[] args) throws IOException {
//		POIFSFileSystem fs = new POIFSFileSystem();
//		
//		//先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
//		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//		BufferedImage bufferedImage = ImageIO.read(new File("abc.jpg"));
//		ImageIO.write(bufferedImage, "jpg", byteArrayOut);
//		//读进一个excel模板
//		FileInputStream fis = new FileInputStream("image.xls");    
//		
//		fs = new POIFSFileSystem(fis);
//		
//		//创建工作薄
//		HSSFWorkbook workbook = new HSSFWorkbook(fs);
//		HSSFSheet sheet = workbook.getSheetAt(0);
//		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
//		HSSFClientAnchor anchor = new HSSFClientAnchor(0,0,1023,255,(short) 0,0,(short)10,10);
//		patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
//		
//		FileOutputStream out = new FileOutputStream("image.xls");
//		workbook.write(out);
//		System.out.println("插入图片完成");
//		
//		fis.close();
		
		 FileOutputStream fileOut = null;     
         BufferedImage bufferImg = null;     
        try {  
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();  
          //加载图片  
            bufferImg = ImageIO.read(new File("abc.jpg"));     
            ImageIO.write(bufferImg, "jpg", byteArrayOut);  
            HSSFWorkbook wb = new HSSFWorkbook();     
            HSSFSheet sheet1 = wb.createSheet("sheet1");    
            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();     
            /** 
                dx1 - the x coordinate within the first cell.//定义了图片在第一个cell内的偏移x坐标，既左上角所在cell的偏移x坐标，一般可设0 
                dy1 - the y coordinate within the first cell.//定义了图片在第一个cell的偏移y坐标，既左上角所在cell的偏移y坐标，一般可设0 
                dx2 - the x coordinate within the second cell.//定义了图片在第二个cell的偏移x坐标，既右下角所在cell的偏移x坐标，一般可设0 
                dy2 - the y coordinate within the second cell.//定义了图片在第二个cell的偏移y坐标，既右下角所在cell的偏移y坐标，一般可设0 
                col1 - the column (0 based) of the first cell.//第一个cell所在列，既图片左上角所在列 
                row1 - the row (0 based) of the first cell.//图片左上角所在行 
                col2 - the column (0 based) of the second cell.//图片右下角所在列 
                row2 - the row (0 based) of the second cell.//图片右下角所在行 
             */  
            HSSFClientAnchor anchor = new HSSFClientAnchor(0,0,1023,255,(short) 0,0,(short)10,10);     
            //插入图片    
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));   
            fileOut = new FileOutputStream("image.xls");     
            // 输出文件   
             wb.write(fileOut);     
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
		
		
}
