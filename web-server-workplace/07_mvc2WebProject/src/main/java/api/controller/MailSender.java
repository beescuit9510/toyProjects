package api.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	// 매개변수로 받은 email주소에, 매개변수로 받은 내용을 보내는 메소드
	public boolean mailSend(String email, String emailTitle, String emailContent) {
		boolean result = false;
//		System.out.println(email);
//		System.out.println(emailTitle);
//		System.out.println(emailContent);

//		<dependency>
//		   <groupId>javax.activation</groupId>
//		   <artifactId>activation</artifactId>
//		   <version>1.4.7</version>
//		</dependency>

		// 이메일 설정
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
//		 prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
//		 prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// 인증정보설정(gmail 로그인)
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa = new PasswordAuthentication("pengsoon20@gmail.com", "qusejrdlrnldudnj");
				return pa;
			}
		});

		// 이메일을 작성해서 전송하는 객체 생성
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSentDate(new Date()); // 메일 전송날짜 설정

			// 보내는사람 정보
			msg.setFrom(new InternetAddress("pengsoon20@gmail.com", "yun"));

			// 받는 사람 정보
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);

			// 이메일 제목 설정
			msg.setSubject(emailTitle, "UTF-8");
			// 이메일 내용 설정
			msg.setContent(emailContent, "text/html;charset=UTF-8");
			// 이메일 전송
			Transport.send(msg);
			result = true;

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}

	
	public String mailSend(String email) {

		Random r = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 7; i++) {

			int low = 0;
			int high = 3;
			int flag = r.nextInt(high-low) + low;
			
			if (flag == 0) {
				int randomNum = r.nextInt(10);
				sb.append(randomNum);
			} else if (flag == 1) {
				char randomChar = (char) (r.nextInt(26) + 65);
				sb.append(randomChar);
			} else if (flag == 2) {
				char randomChar = (char) (r.nextInt(26) + 97);
				sb.append(randomChar);
			}
		}

		System.out.println("생성한 랜덤코드 " + sb.toString());

		boolean result = false;

		// 이메일 설정
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
//		 prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
//		 prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		// 인증정보설정(gmail 로그인)
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa = new PasswordAuthentication("pengsoon20@gmail.com", "qusejrdlrnldudnj");
				return pa;
			}
		});

		// 이메일을 작성해서 전송하는 객체 생성
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSentDate(new Date()); // 메일 전송날짜 설정

			// 보내는사람 정보
			msg.setFrom(new InternetAddress("pengsoon20@gmail.com", "yun"));

			// 받는 사람 정보
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO, to);

			// 이메일 제목 설정
			msg.setSubject("인증번호 전송", "UTF-8");
			// 이메일 내용 설정
			msg.setContent("<h1>안녕하세요 KH정보보육원 입니다.</h1>" + "<h3>인증번호는 [<span style='color:red;'>" + sb.toString()
					+ "</span>] 입니다.</h3>", "text/html;charset=UTF-8");
			// 이메일 전송
			Transport.send(msg);
			result = true;

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(result) {
			return sb.toString();
		}
		return null;

	}

}
