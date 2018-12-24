package com.sendmsg.test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.sendmsg.utils.MessageSendUtil;
/*
 * 
 * 测试发送短信
 * 
 */
public class SendMsgTest {

	
	@Test
	public void test() throws UnsupportedEncodingException{
		String organizationData = MessageSendUtil.organizationData("15013638692", "测试测试");
		
		System.out.println(organizationData);
		String sendSMS = MessageSendUtil.sendSMS("18928438598", "测试测试");
		System.out.println(sendSMS);
	}
	

	public static void main(String[] args) {
		System.out.println("aaa\n\r");
		System.out.println("123213");
		System.out.println("a=\r"+(1+2));
	}
	
	
}
