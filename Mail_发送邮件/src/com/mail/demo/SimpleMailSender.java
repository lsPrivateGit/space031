package com.mail.demo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.pingan.worknet.mail.FileTemplate;

/**
 * 简单邮件（不带附件的邮件）发送器 
 */
public class SimpleMailSender {
	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			//Address to = new InternetAddress(buildAddressArray(mailInfo.getToAddress()));
			//mailMessage.setRecipient(Message.RecipientType.TO, to);
			mailMessage.setRecipients(Message.RecipientType.TO, buildAddressArray(mailInfo.getToAddress()));  
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			MailHtmlTemplate htmlTemplate = new MailHtmlTemplate(mailInfo);
			List fileTemplateList = new ArrayList();
			Multipart multiPart = getMultiPart(htmlTemplate, fileTemplateList);
			// 设置邮件消息的主要内容
			//String mailContent = mailInfo.getContent();
			//mailMessage.setText(mailContent);
			mailMessage.setContent(multiPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			//Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			//mailMessage.setRecipient(Message.RecipientType.TO, to);
			mailMessage.setRecipients(Message.RecipientType.TO, buildAddressArray(mailInfo.getToAddress()));
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 获取标准邮件对象
	 * @throws MessagingException 
	 */
	private MimeMultipart getMultiPart(MailHtmlTemplate htmlTemplate, List fileTemplateList)
			throws MessagingException {
		MimeMultipart mimeMultiPart = new MimeMultipart("related");
		mimeMultiPart.setSubType("related");
		mimeMultiPart.addBodyPart(htmlTemplate.getMimeBodyPart());
		System.out.println("获取HTML模板成功");
		for (Iterator it = buildBodyPart(fileTemplateList).iterator(); it.hasNext();) {
			MimeBodyPart bodyPart = (MimeBodyPart) it.next();
			mimeMultiPart.addBodyPart(bodyPart);
		}
		System.out.println("获取文件模板成功");
		return mimeMultiPart;
	}
	
	/**
	 * 文件列表fileList<FileTemplate>转换成标准的MimeBodyPart列表<MimeBodyPart>
	 * @return List
	 * @throws MessagingException
	 */
	private static List buildBodyPart(List fileTemplateList) throws MessagingException {
		List bodyList = new ArrayList(fileTemplateList.size());
		for (Iterator it = fileTemplateList.iterator(); it.hasNext();) {
			FileTemplate fileTemplate = (FileTemplate) it.next();
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setHeader("Content-ID", fileTemplate.getContentId());
			try {
				bodyPart.setFileName(MimeUtility.encodeWord(fileTemplate.getFileName(), "gb2312", null));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			bodyPart.setDataHandler(fileTemplate.getDataHandler());
			bodyList.add(bodyPart);
		}
		System.out.println("文件模板转变成标准邮件消息对象成功");
		return bodyList;
	}
	
	
	private static Address[] buildAddressArray(List emailList) throws AddressException {
		if (emailList == null || emailList.isEmpty())
			return null;
		Address[] result = new Address[emailList.size()];
		for (int i = 0; i < emailList.size(); i++) {
			String email = (String) emailList.get(i);
			result[i] = new InternetAddress(email);
		}
		return result;
	}
	
	/**
	 * 以文本格式发送邮件 (支持群发,带附件)
	 * @param senderInfo 待发送的邮件的信息 
	 * @return
	 */
	public static boolean sendMail(MailSenderInfo senderInfo){
		boolean flag = false;
		Transport transport = null;
		// 判断是否需要身份验证
		MyAuthenticator authenticator = null;
		Properties props = senderInfo.getProperties();
		if(senderInfo.isValidate()){
			// 如果需要身份认证，则创建一个密码验证器   
			authenticator = new MyAuthenticator(senderInfo.getUserName(), senderInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getInstance(props, authenticator);
		
		try {
			// 根据session创建一个邮件消息
			Message sendMailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(senderInfo.getFromAddress());
			// 设置邮件消息的发送者
			sendMailMessage.setFrom(from);
			
			// 创建邮件接收者地址
			sendMailMessage.setRecipients(Message.RecipientType.TO, buildAddressArray(senderInfo.getToAddress()));
			
			// 设置邮件抄送者地址
			/*String[] toCCs = senderInfo.getToCarbonCopyAddress();
			if(toCCs != null && toCCs.length != 0){
				InternetAddress[] toCC = new InternetAddress[toCCs.length];
				// 设置邮件消息的发送者
				for (int i = 0; i < toCCs.length; i++) {
					toCC[i] = new InternetAddress(toCCs[i]);
				}
				sendMailMessage.addRecipients(Message.RecipientType.CC, toCC);
			}*/
			
			// 设置邮件密送者地址
			/*String[] toBCCs = senderInfo.getToBlindCarbonCopyAddress();
			if(toBCCs != null && toBCCs.length != 0){
				InternetAddress[] toBCC = new InternetAddress[toBCCs.length];
				for (int i = 0; i < toBCCs.length; i++) {
					toBCC[i] = new InternetAddress(toBCCs[i]);
				}
				sendMailMessage.addRecipients(Message.RecipientType.BCC, toBCC);
			}*/
			
			// 设置邮件主题
			sendMailMessage.setSubject(MimeUtility.encodeText(senderInfo.getSubject(),"utf-8","B"));
			// 设置邮件内容
			//sendMailMessage.setText(senderInfo.getContent());
			Multipart multipart = new MimeMultipart();
			// 邮件文本内容
			if(senderInfo.getContent() != null && !"".equals(senderInfo.getContent())){
				MailHtmlTemplate htmlTemplate = new MailHtmlTemplate(senderInfo);
				BodyPart part = htmlTemplate.getMimeBodyPart();
				//part.setContent(senderInfo.getContent(), "text/plain;charset=utf-8");//设置邮件文本内容
				multipart.addBodyPart(part);
			}
			
			// 附件
			String attachFileNames = senderInfo.getAttachFileNames();
			if(attachFileNames != null || !(attachFileNames.equals(""))){
				BodyPart part = new MimeBodyPart();
				// 根据文件名获取数据源
				DataSource dataSource = new FileDataSource(attachFileNames);
				DataHandler dataHandler = new DataHandler(dataSource);
				// 得到附件本身并至入BodyPart
				part.setDataHandler(dataHandler);
				// 得到文件名同样至入BodyPart
				part.setFileName(MimeUtility.encodeText(dataSource.getName()));
				multipart.addBodyPart(part);
			}
			
			sendMailMessage.setContent(multipart);
			// 设置邮件发送的时间
			sendMailMessage.setSentDate(new Date());
			// 发送邮件
			//Transport.send(sendMailMessage);
			transport = sendMailSession.getTransport("smtp");
			transport.connect(senderInfo.getMailServerHost(),senderInfo.getUserName(), senderInfo.getPassword());
			transport.send(sendMailMessage,sendMailMessage.getAllRecipients());
			transport.close();
			
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				transport.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	
	/**
	 * 以文本格式发送邮件 (支持群发,带附件,支持抄送)
	 * @param senderInfo 待发送的邮件的信息 
	 * @return
	 */
	public static boolean sendWillMail(MailSenderInfo senderInfo){
		boolean flag = false;
		Transport transport = null;
		// 判断是否需要身份验证
		MyAuthenticator authenticator = null;
		Properties props = senderInfo.getProperties();
		if(senderInfo.isValidate()){
			// 如果需要身份认证，则创建一个密码验证器   
			authenticator = new MyAuthenticator(senderInfo.getUserName(), senderInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getInstance(props, authenticator);
		
		try {
			// 根据session创建一个邮件消息
			Message sendMailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(senderInfo.getFromAddress());
			// 设置邮件消息的发送者
			sendMailMessage.setFrom(from);
			
			// 创建邮件接收者地址
			sendMailMessage.setRecipients(Message.RecipientType.TO, buildAddressArray(senderInfo.getToAddress()));
			// 设置邮件抄送者地址
			ArrayList toCCs = senderInfo.getToCarbonCopyAddress();
			System.out.println("------toCCs=---------"+toCCs);  
			if(toCCs != null && !"".equals(toCCs)){
			    
				sendMailMessage.addRecipients(Message.RecipientType.CC, buildAddressArray(toCCs));
			}
			// 设置邮件密送者地址
			/*String[] toBCCs = senderInfo.getToBlindCarbonCopyAddress();
			if(toBCCs != null && toBCCs.length != 0){
				InternetAddress[] toBCC = new InternetAddress[toBCCs.length];
				for (int i = 0; i < toBCCs.length; i++) {
					toBCC[i] = new InternetAddress(toBCCs[i]);
				}
				sendMailMessage.addRecipients(Message.RecipientType.BCC, toBCC);
			}*/
			System.out.println("------1--------"); 
			// 设置邮件主题
			sendMailMessage.setSubject(MimeUtility.encodeText(senderInfo.getSubject(),"utf-8","B"));
			// 设置邮件内容
			//sendMailMessage.setText(senderInfo.getContent());
			Multipart multipart = new MimeMultipart();
			// 邮件文本内容
			if(senderInfo.getContent() != null && !"".equals(senderInfo.getContent())){
				MailHtmlTemplate htmlTemplate = new MailHtmlTemplate(senderInfo);
				BodyPart part = htmlTemplate.getMimeBodyPart();
				//part.setContent(senderInfo.getContent(), "text/plain;charset=utf-8");//设置邮件文本内容
				multipart.addBodyPart(part);
			}
			System.out.println("------2--------"); 
			// 附件
			String attachFileNames = senderInfo.getAttachFileNames();
			if(attachFileNames != null  && !(attachFileNames.equals(""))){
				String[] sFiles = attachFileNames.split(",");
				for(int i=0;i<sFiles.length;i++){
				BodyPart part = new MimeBodyPart();
				// 根据文件名获取数据源
				DataSource dataSource = new FileDataSource(sFiles[i]);
				DataHandler dataHandler = new DataHandler(dataSource);
				// 得到附件本身并至入BodyPart
				part.setDataHandler(dataHandler);
				// 得到文件名同样至入BodyPart
				part.setFileName(MimeUtility.encodeText(dataSource.getName()));
				multipart.addBodyPart(part);
				}
			}
			System.out.println("------3--------"+attachFileNames); 
			sendMailMessage.setContent(multipart);
			System.out.println("------4--------"); 
			// 设置邮件发送的时间
			sendMailMessage.setSentDate(new Date());
			System.out.println("------5--------"); 
			// 发送邮件
			//Transport.send(sendMailMessage);
			transport = sendMailSession.getTransport("smtp");
			System.out.println("------6--------"); 
			transport.connect(senderInfo.getMailServerHost(),senderInfo.getUserName(), senderInfo.getPassword());
			System.out.println("------7--------"); 
			transport.send(sendMailMessage,sendMailMessage.getAllRecipients());
			System.out.println("------8--------"); 
			transport.close();
			
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				transport.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
}