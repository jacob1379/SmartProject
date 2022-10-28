package com.example.demo.security;

import java.io.*;


import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import com.example.demo.consumer.dao.ConsumerDao;
import com.example.demo.consumer.entity.Consumer;

//로그인에 성공했다면 SS가 Authentication을 생성...but
//로그인 실패 처리 - 아이디를 request에서 꺼낸다
//로그인 실패 횟수 증가
//5회이상 실패면 계정 블록
//오류메시지를 세션에 담은 다음 이동
@Component
public class ConsumerLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	@Autowired
	private ConsumerDao dao;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		String cId = request.getParameter("cId");
		
		// Consumer를 읽어오는 이유는 로그인 실패 횟수가 필요해서
		// 아이디가 틀리면 consumer가 없음 그래서 null로 바꾼다
		Consumer consumer = dao.cFindId(cId).orElse(null);
		HttpSession session = request.getSession(); // client request에서 client를 식별하고, 해당 client 정보를 저장
		
		// AuthenticationException의 구체적인 이유를 확인
		// BadCredentialsException - 아이디 또는 비밀번호가 틀렸다
		// DisabledException - 계정이 블록되었다
		if(exception instanceof BadCredentialsException) {
			if(consumer!=null) {
				// 비밀번호가 틀렸다면
				Integer loginFailCnt = consumer.getCLoginFailCount();
				if(loginFailCnt<5) {
					dao.cMemberUpdate(Consumer.builder().cId(cId).cLoginFailCount(loginFailCnt+1).build());
					session.setAttribute("msg", (loginFailCnt+1) + "회 로그인에 실패했습니다");
				}
			} else { // 아이디가 틀렸다면
				session.setAttribute("msg", "로그인에 실패했습니다");
			}
		} else if(exception instanceof DisabledException) {
			session.setAttribute("msg", "블록된 계정입니다. 관리자에게 문의하세요");
		}
		
		new DefaultRedirectStrategy().sendRedirect(request, response, "/consumer/login");
	}
}
