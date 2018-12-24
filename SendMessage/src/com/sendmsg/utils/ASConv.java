package com.sendmsg.utils;

import org.junit.Test;

public class ASConv
{

    public ASConv()
    {
    	
    }

	public static String decode(String s,String encoding) throws Exception 
	{
		if(s==null) return s;
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<s.length(); i++) 
		{
			char c = s.charAt(i);
			switch (c) 
			{
			case '+':
				sb.append(' ');
				break;
			case '%':
				try 
				{
					sb.append((char)Integer.parseInt(s.substring(i+1,i+3),16));
				}
				catch (NumberFormatException e) 
				{
					throw new IllegalArgumentException();
				}
				i += 2;
				break;
			default:
				sb.append(c);
				break;
			}
		}
		
		String result = sb.toString();
		byte[] inputBytes = result.getBytes("8859_1");
		return new String(inputBytes,encoding);		
	}
	
	
	@Test
	public void test() throws Exception{
		String str="中国人";
		
		String encoding="gbk";
		String decode = ASConv.decode(str, encoding);
		
		System.out.println(decode);
	}

}
