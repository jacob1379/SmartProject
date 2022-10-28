package com.example.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

// 403(서버에서 허락되지 않음)을 처리하는 스프링 시큐리티 객체
@Component
public class ConsumerAccessDeniedHandler implements AccessDeniedHandler{
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// 그런데.... 403이 발생했을 때
		// MVC라면 에러페이지로 이동, REST면 에러메시지를 쏴준다
		
		// AJAX라면 -> AJAX는 XMLHttpRequest라는 요청 객체를 사용한다
		// 그런데 우리는 위 객체를 한번도 못봤어 -> jQuery에서 감췄다
		if("XMLHttpResquest".equals(request.getHeader("x-requested-with"))) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			response.setContentType("text/plain;charset=utf-8");
			response.getWriter().print("권한없음 - 작업을 수행할 권한이 없습니다");
		} else {
			// mvc라면 redirect -> 세션에 오류 메시지를 저장하고 /로 이동시키자
			HttpSession session = request.getSession();
			session.setAttribute("msg", "작업을 수행할 권한이 없습니다");
			response.sendRedirect("/");
		}
	}

}
