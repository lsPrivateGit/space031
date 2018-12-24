package com.mail.demo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.amarsoft.account.sysconfig.SystemConfig;
import com.amarsoft.are.ARE;
import com.amarsoft.impl.szpab.ei.EnvTools;
import com.dashuf.filestore.impl.CommonFileStoreDAOStaticImpl;
import com.dashuf.linkBank.service.MailSendHttpService;

public class MailSender {

	/*@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.105.155:1529:t0dspm", "dspmopr", "Dsf##xd90");
		ARE.init();

		SystemConfig.loadSystemConfig(true, conn);

		HashMap mailInfo = new HashMap<String, Object>();
		mailInfo.put("ToAddress", "ex-zhurongzeng@dashuf.com");
		mailInfo.put("Subject", "DSPM测试邮件");
		mailInfo.put("Description", "DSPM测试邮件");

		MailSender sender = new MailSender(mailInfo);
		sender.mailSend();
	}*/

	private HashMap hashMap = null;
	private SimpleMailSender sms;
	private MailSenderInfo mailInfo;

	public MailSender(HashMap<String, String> hashMap) {
		this.hashMap = hashMap;
		init();
	}

	/*
	 * 进行初始化数据
	 */
	private void init() {
		mailInfo = new MailSenderInfo();
		// 设置邮件发送的IP
		mailInfo.setMailServerHost(EnvTools.getProperty("MAIL_SERVERHOST").trim());
		// 设置邮件发送的端口
		mailInfo.setMailServerPort(EnvTools.getProperty("MAIL_SERVERPORT").trim());
		mailInfo.setValidate(true);
		// 设置邮件发送的用户名
		mailInfo.setUserName(EnvTools.getProperty("MAIL_SYSTEMSERVER").trim());
		// 设置邮件发送的邮箱密码
		mailInfo.setPassword(EnvTools.getProperty("MAIL_SYSTEMPASSWORD").trim());
		// 发件人邮箱
		mailInfo.setFromAddress(EnvTools.getProperty("MAIL_SYSTEMSERVER").trim());
		System.out.println("---1==" + getMailList(getAttribute("ToAddress", "")) + "----2==-" + getAttribute("Subject", "") + "---3===" + getAttribute("Description", ""));
		// 收件人邮箱
		mailInfo.setToAddress(getMailList(getAttribute("ToAddress", "")));
		// 设置发送邮件的标题
		mailInfo.setSubject(getAttribute("Subject", ""));
		// 设置发送邮件的内容
		mailInfo.setContent(getAttribute("Description", ""));
		mailInfo.setLinkURL("http://prd.dashuf.com:30069/DSPM/logon.html");

	}

	public String mailSend() {
		String sReturn = "";
		// 判断是否是新旧邮箱
		String flag = isConfrimation(mailInfo.getFromAddress());
		if ("0".equals(flag)) {
			sReturn = sendMail();
		} else {
			sms = new SimpleMailSender();
			try {
				// 发送文体格式
				if (sms.sendTextMail(mailInfo)) {
					sReturn = "succeed";
				} else {
					sReturn = "failure";
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				sReturn = "failure";
			}
		}
		return sReturn;
	}

	public String mailSendWint() {
		// 添加附件
		mailInfo.setAttachFileNames(getAttribute("AttachFileNames", ""));
		String sReturn = "";
		// 判断是否是新旧邮箱
		String flag = isConfrimation(mailInfo.getFromAddress());
		if ("0".equals(flag)) {
			sReturn = sendMail();
		} else {
			sms = new SimpleMailSender();
			try {
				sms.sendMail(mailInfo);// 发送文体格式
				sReturn = "succeed";
			} catch (Exception ex) {
				ex.printStackTrace();
				sReturn = "failure";
			}
		}

		return sReturn;
	}

	public ArrayList<String> getMutioMailList(String smailList) {
		if (smailList == null)
			return null;

		String smailArray[] = smailList.split(";");
		ArrayList<String> mailList = new ArrayList<String>();
		for (int i = 0; i < smailArray.length; i++) {
			if (smailArray[i] != null && !smailArray[i].equals("") && isMail(smailArray[i]) && (!(smailArray[i].trim().equals(EnvTools.getProperty("MAIL_DOMAINNAME").trim())))) {
				mailList.add(smailArray[i].trim());
			}
		}

		return mailList;
	}

	public String mailSendWill() {
		String sReturn = "";
		// 收件人
		mailInfo.setToAddress(getMutioMailList(getAttribute("ToAddress", "")));
		// 抄送人
		mailInfo.setToCarbonCopyAddress(getMutioMailList(getAttribute("ToCarbonCopyAddress", "")));
		// 发件人
		mailInfo.setFromAddress(getAttribute("FromAddress", ""));
		// 添加附件
		mailInfo.setAttachFileNames(getAttribute("AttachFileNames", ""));
		System.out.println("---收件人==" + getMutioMailList(getAttribute("ToAddress", "")) + "----抄送人==-" + getMutioMailList(getAttribute("ToCarbonCopyAddress", "")) + "---3===" + getAttribute("FromAddress", ""));
		// mailInfo.setToCarbonCopyAddress(getMailList(getAttribute("ToCarbonCopyAddress","")));
		// 判断是否是新旧邮箱
		String flag = isConfrimation(mailInfo.getFromAddress());
		if ("0".equals(flag)) {
			sReturn = sendMail();
		} else {
			sms = new SimpleMailSender();
			try {
				sms.sendWillMail(mailInfo);// 发送文体格式
				sReturn = "succeed";
			} catch (Exception ex) {
				ex.printStackTrace();
				sReturn = "failure";
			}
		}
		
		return sReturn;
	}

	/*
	 * 
	 * @return 获取键对应值
	 */
	public String getAttribute(String sKey) {
		return (String) this.hashMap.get(sKey);
	}

	/*
	 * 
	 * @return 获取键对应值，如果该键不存在，返回默认值
	 */

	public String getAttribute(String sKey, String sDefault) {
		String sValue = (String) this.hashMap.get(sKey);
		if (sValue == null || sValue.equals("")) {
			sValue = sDefault;
		}
		
		return sValue;
	}

	/*
	 * 
	 * 将邮件字符串按逗号分割，产生邮件ArrayList
	 * 
	 * @param smailList 邮件地址（以逗号分割的）
	 * 
	 * @return ArrayList的邮件地址对象
	 */

	public ArrayList<String> getMailList(String smailList) {
		if (smailList == null)
			return null;

		String smailArray[] = smailList.split(",");
		ArrayList<String> mailList = new ArrayList<String>();
		for (int i = 0; i < smailArray.length; i++) {
			if (smailArray[i] != null && !smailArray[i].equals("") && isMail(smailArray[i]) && (!(smailArray[i].trim().equals(EnvTools.getProperty("MAIL_DOMAINNAME").trim())))) {
				mailList.add(smailArray[i].trim());
			}
		}

		return mailList;
	}

	/*
	 * 校验邮件正则表达式
	 * 
	 * @param address 单个邮件地址
	 */

	public boolean isMail(String address) {
		Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
		Matcher matcher = pattern.matcher(address);
		return matcher.matches();
	}

	// 判断 是否 邮箱是 cn 邮箱
	public String isConfrimation(String list) {
		String flag = "1";
		if (list.indexOf(".cn") != -1) {
			flag = "0";
		}

		return flag;
	}

	// 将 List 里面的收件人用分号分隔
	public String getToaddress(List list) {
		String toAddress = "";
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				toAddress = toAddress + list.get(i) + ";";
			}
		}
		return toAddress;
	}

	// 通过邮件平台 发送邮件
	public String sendMail() {
		// 通过邮件平台 发送邮件
		String sReturn = "";
		JSONObject mailJson = new JSONObject();
		// 标题
		mailJson.put("subject", mailInfo.getSubject());// 邮件主题
		mailJson.put("toAddress", getToaddress(mailInfo.getToAddress()));// 收件人
		mailJson.put("userName", mailInfo.getUserName());// 发件人
		mailJson.put("toBlindCarBonCopy", "");// 密送地址
		mailJson.put("toCarBonCopy", getToaddress(mailInfo.getToCarbonCopyAddress()));// 抄送地址
		mailJson.put("content", mailInfo.getContent());
		
		String filepath = mailInfo.getAttachFileNames();
		if (!"".equals(filepath) && filepath != null) {
			// 将 文件 共享到共享 平台上面
			CommonFileStoreDAOStaticImpl fileStoreDao = new CommonFileStoreDAOStaticImpl();
			String fileId = fileStoreDao.newFile("f", "zip");// 新建文件id
			File file = new File(filepath);
			fileStoreDao.modifyFile(fileId, file);// 把zip内容写入文件id
			mailJson.put("attachFiles", fileId);
			mailJson.put("attachFilesName", mailInfo.getAttachFileNames().substring(
					mailInfo.getAttachFileNames().lastIndexOf("/") + 1, mailInfo.getAttachFileNames().indexOf(".")));
		} else {
			mailJson.put("attachFiles", "");
			mailJson.put("attachFilesName", "");
		}

		System.out.println("调用邮件平台参数：" + mailJson.toJSONString());
		JSONObject mailresponse = null;
		try {
			mailresponse = MailSendHttpService.doPost(mailJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String mailcode = mailresponse.getString("code");
		if ("00".equals(mailcode)) {
			sReturn = "succeed";
		} else {
			sReturn = mailcode;
		}
		return sReturn;
	}
}
