package cn.com.webxml.test;

import cn.com.webxml.MobileCodeWS;
import cn.com.webxml.MobileCodeWSSoap;

public class MobileWebServiceTest {
	
	public static void main(String[] args) {
		MobileCodeWS mobileCodeWS = new MobileCodeWS();
		MobileCodeWSSoap service = mobileCodeWS.getMobileCodeWSSoap();
		String mobileCodeInfo = service.getMobileCodeInfo("15013638692", null);
		System.out.println(mobileCodeInfo);
		
	}
}
