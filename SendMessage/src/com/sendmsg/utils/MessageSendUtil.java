package com.sendmsg.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/**
 * 发送短信公共类
 * @author ex-sunjiamin
 *
 */
public class MessageSendUtil {

	public static final String SEND_URL =  "http://121.40.102.192:80/GsmsHttp";//IP和端口号需要更改为正式环境下的。
	
	public static String username = "69463:admin";
	
	public static String password = "37911857";
	
	/**
	 * 发送短信
	 * @param cellPhone 手机号码
	 * @param message   短信内容
	 * @return 以OK开始发送成功，以ERROR开始发送失败
	 */
	public static String sendSMS(String cellPhone,String message){
		try {
			//getUserNameAndPassword();
			URL url = new URL(SEND_URL);  
			URLConnection con = url.openConnection();  
			con.setDoOutput(true); 
			con.setRequestProperty("Pragma:", "no-cache");  
			con.setRequestProperty("Cache-Control", "no-cache");  
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
			con.setConnectTimeout(300000);
			
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(),"gbk");  
			String sendSmsData = organizationData(cellPhone,message);
			out.write(sendSmsData);  
			out.flush();  
			out.close();  
			  
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));  
			String line = "";  
			StringBuffer buf = new StringBuffer();  
			while ( (line = br.readLine()) != null ) {  
			    buf.append(line);  
			}
			if(null != buf.toString() && !buf.toString().equals("") && buf.toString().startsWith("OK")){
	        	System.out.println("短信发送成功：["+cellPhone+"] 短信内容："+message);
	        }else{
	        	System.out.println("短信发送失败：["+cellPhone+"] 短信内容："+message);
	        }
			return buf.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "ERROR";
		} catch (IOException e) {
			e.printStackTrace();
			return "ERROR";
		}  
	}
	
	public static String organizationData(String cellPhone,String message) throws UnsupportedEncodingException{
		StringBuilder sendBuilder = new StringBuilder();
		sendBuilder.append("username="+username);//机构ID:用户登录名
		sendBuilder.append("&password="+password);//密码
		sendBuilder.append("&from=001");//发送手机号码
		sendBuilder.append("&to="+cellPhone);//接收手机号，多个号码间以逗号分隔且最大不超过100个号码
		sendBuilder.append("&content="+message);//发送内容,标准内容不能超过70个汉字
		System.out.println(sendBuilder.toString());
		return sendBuilder.toString();
	} 
}
