package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //설정파일 or Bean을 등록하기 위한 어노테이션
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
// MethodSecurity용 설정이 따로 필요한데 이때 사용, 속성값들의 기본은 false
// prePostEnabled : @PreAuthorize, @PostAuthorize(특정 메소드 호출 전,후 권한을 확인) 어노테이션을 사용하여 인가 처리를 하고 싶을 때 사용하는 옵션
// securedEnabled : @Secured(특정 메소드 호출 전 권한을 확인, 스프링에서 지원) 어노테이션을 사용하여 인가 처리를 하고 싶을 때 사용하는 옵션
// jsr250Enabled : @RoleAllowed(특정 메소드 호출 전 권한을 확인, 자바에서 지원) 어노테이션을 사용하여 인가 처리를 하고 싶을 때 사용하는 옵션
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	// WebSecurityConfigurerAdapter 이용 불가
	// 이용 방법 : 1. WebSecurityConfigurerAdapter 상속
	// 			 2. configure 메소드 오버라이딩
	// 			 3. SecurityFilterChain을 Bean으로 등록 @EnableWebSecurity를 통한 자동등록
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*
	 * @Override // user권한의 아이디 임의 생성 protected void
	 * configure(AuthenticationManagerBuilder auth) throws Exception {
	 * auth.inMemoryAuthentication().withUser("ACCOUNT111").password(passwordEncoder
	 * .encode("1234")); }
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// httpSecurity : 세부적인 보안기능을 설정할 수 있는 API 제공 클래스
		http.csrf().disable(); // csrf: html위조를 방어하는 기술. 일단 꺼놓고 작업
		http.formLogin(); // mvc 로그인 활성화(자동설정)
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
	}
}
