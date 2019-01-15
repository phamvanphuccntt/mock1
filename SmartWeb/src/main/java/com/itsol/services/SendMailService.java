package com.itsol.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.stereotype.Service;

@Service

public class SendMailService {
	public  boolean senMail(String to,String passWord , String userName){


		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("khaittph05424@fpt.edu.vn", "trantuankhai");
			}
		});
		try {
			Message msg = new MimeMessage(session);


			  // -- Set the FROM and TO fields --
			     msg.setFrom(new InternetAddress("khaittph05424@fpt.edu.vn"));
			     msg.setRecipients(Message.RecipientType.TO, 
	                      InternetAddress.parse(to,false));
			     msg.setSubject("Kích Hoạt Tài Khoản Ứng Cử Viên");
			     msg.setContent("<h4>Mật khẩu tài khoản của bạn là</h4>: "+ passWord +"<br>" +"<h4>Nhấp vào link để kích hoạt tài khoản: </h4>"+"<a href=\"http://localhost:8080/account/active/"+userName+"\""+ ">"+"Bấm Vào Đây"+"</a>","text/html;charset=utf-8");
			     Transport.send(msg);
			     System.out.println("Message sent.");

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;



}
//	public static void main(String[] args) {
//		if(senMail("phamvanphuccnt@gmai.com", "hihi", "haha")) {
//			System.out.println("thành công");
//		}else {
//			System.out.println("thất bại");
//		}
//	}
}
	

