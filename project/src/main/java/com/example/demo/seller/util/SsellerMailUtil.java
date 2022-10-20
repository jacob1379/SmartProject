package com.example.demo.seller.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.example.demo.seller.dto.SsellerMail;

@Component
public class SsellerMailUtil {
	@Autowired
	private JavaMailSender javaMailSender;
	/*
	public void sendMail() {
        
        // 수신 대상을 담을 ArrayList 생성
        ArrayList<String> toUserList = new ArrayList<>();
        
        // 수신 대상 추가
        toUserList.add("projectseller1111@gmail.com");
        toUserList.add("redeyeace@naver.com");
        
        // 수신 대상 개수
        int toUserSize = toUserList.size();
        
        // SimpleMailMessage (단순 텍스트 구성 메일 메시지 생성할 때 이용)
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        
        // 수신자 설정
        simpleMessage.setTo((String[]) toUserList.toArray(new String[toUserSize]));
        
        // 메일 제목
        simpleMessage.setSubject("Subject Sample");
        
        // 메일 내용
        simpleMessage.setText("Text Sample");
        
        // 메일 발송
        javaMailSender.send(simpleMessage);
    }
	*/
	
	// JavaMailSender를 이용한 메일 설정 및 보내는 메서드
	private void sSendMail(SsellerMail mail) throws javax.mail.MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, false, "utf-8");
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getText(), true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	// 아이디 찾기 안내 메일 
	public void sSendFindIdMail(String from, String to, String sId) throws javax.mail.MessagingException {
		SsellerMail mail = SsellerMail.builder().from(from).to(to).subject("아이디 확인 메일").build();
		
		String message = new StringBuffer("<p>아이디를 찾았습니다</p>").append("<p>당신의 아이디 : ").append(sId).toString();
		sSendMail(mail.setText(message));
	}
	
	// 임시 비밀번호 보내는 메서드
	public void sSendResetPasswordMail(String from, String to, String sPassword) throws javax.mail.MessagingException {
		SsellerMail mail = SsellerMail.builder().from(from).to(to).subject("임시비밀번호").build();
		String message = new StringBuffer("<p>임시비밀번호를 발급했습니다</p>").append("<p>임시 비밀번호 : ").append(sPassword).append("</p>").toString();
		sSendMail(mail.setText(message));
	}
	
	// 체크코드를 받아서 링크를 만든 다음 메일로 보내는 함수
	public void sSendCheckMail(String from, String to, String sCheckcode) throws javax.mail.MessagingException {
		SsellerMail mail = SsellerMail.builder().from(from).to(to).subject("가입 확인 메일").build();
		StringBuffer buf = new StringBuffer("<p>회원가입을 위한 안내 메일입니다</p>");
		buf.append("<p>가입 확인을 위해 아래 링크를 클릭하세요</p>");
		buf.append("<p>가입 확인 링크 :");
		buf.append("<a href='http://localhost:8083/seller/check/join?sCheckcode=");
		buf.append(sCheckcode);
		buf.append("'>클릭하세요</a></p>");
		mail.setText(buf.toString());
		sSendMail(mail);
	}
}
