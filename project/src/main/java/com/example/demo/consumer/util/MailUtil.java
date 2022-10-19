package com.example.demo.consumer.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.demo.consumer.dto.Mail;

@Component
public class MailUtil {
	@Autowired
	private JavaMailSender javaMailSender;
	
	private void sendMail(Mail mail) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, false, "utf-8");
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getText(), true);
			javaMailSender.send(message);
		} catch(MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void sendFindIdMail(String from, String to, String cId) {
		Mail mail = Mail.builder().from(from).to(to).subject("아이디 확인 메일").build();
		String message = new StringBuffer("<p>아이디를 찾았습니다</p>")
				.append("<p>당신의 아이디 : ")
				.append(cId)
				.toString();
		sendMail(mail.setText(message));
	}
	
	public void sendCheckMail(String from, String to, String checkcode) {
		Mail mail = Mail.builder().from(from).to(to).subject("가입 확인 메일").build();
		StringBuffer buf = new StringBuffer("<p>회원가입을 위한 안내 메일입니다<p>");
		buf.append("<p>가입 확인을 위해 아래 링크를 클릭하세요<p>");
		buf.append("<p>가입 확인 링크 :");
		buf.append("<a href='http://localhost:8087/consumer/check/join?checkcode=");
		buf.append(checkcode);
		buf.append("'>클릭하세요</a></p>");
		mail.setText(buf.toString());
		sendMail(mail);
	}
}
