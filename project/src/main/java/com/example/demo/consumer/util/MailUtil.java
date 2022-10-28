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
	
	
	// JavaMailSender를 이용해 메일을 설정하고 보내는 메소드
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
	
	// 아이디 찾기 안내 메일 보내는 메소드
	public void sendFindIdMail(String from, String to, String cId) {
		Mail mail = Mail.builder().from(from).to(to).subject("아이디 확인 메일").build();
		
		// 자바에서 값을 변경할 수 없는 객체를 불변(immutable) 객체
		// 대표적인 불변 객체는 String : 아래 코드는 3개의 String객체를 만든다
		// String str = "Hello" + " " + "Java";
		// 문자열 편집 작업은 문자열 변수 객체로(StringBuffer, StringBuilder)
		String message = new StringBuffer("<p>아이디를 찾았습니다</p>")
				.append("<p>당신의 아이디 : ")
				.append(cId)
				.toString();
		sendMail(mail.setText(message));
	}
	
	// 체크코드를 받아서 링크를 만든 후 메일로 보내는 함수
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
	
	// 임시 비밀번호 보내는 메소드
	public void sendFindPasswordMail(String from, String to, String password) {
		Mail mail = Mail.builder().from(from).to(to).subject("임시비밀번호").build();
		String message = new StringBuffer("<p>임시 비밀번호를 발급했습니다</p>")
				.append("<p>임시비밀번호 :").append(password).append("</p>").toString();
		sendMail(mail.setText(message));
	}
}
