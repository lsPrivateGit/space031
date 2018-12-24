package cn.com.webxml.test;


import cn.com.webxml.ArrayOfString;
import cn.com.webxml.IpAddressSearchWebService;
import cn.com.webxml.IpAddressSearchWebServiceSoap;

public class IpWebServiceTest {
	
	public static void main(String[] args) {
		IpAddressSearchWebService ipAddressSearchWebService = new IpAddressSearchWebService();
		IpAddressSearchWebServiceSoap ipAddressSearchWebServiceSoap = ipAddressSearchWebService.getIpAddressSearchWebServiceSoap();
		ArrayOfString countryCityByIp = ipAddressSearchWebServiceSoap.getCountryCityByIp("8.8.8.8");
		String str = ipAddressSearchWebServiceSoap.getCountryCityByIp("192.168.1.1").toString();
		System.out.println(str);
		 System.out.println(countryCityByIp.toString());
	}
}
