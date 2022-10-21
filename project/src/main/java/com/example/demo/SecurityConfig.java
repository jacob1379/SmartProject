package com.example.demo;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.security.ConsumerAccessDeniedHandler;
import com.example.demo.security.ConsumerLoginFailureHandler;
import com.example.demo.security.ConsumerLoginSuccessHandler;

@EnableWebSecurity
public class SecurityConfig {
	// 관리자는 /adim/**으로 접근한다
	@Order(1)
	@Configuration
	public static class AdminSecurityConfing extends WebSecurityConfigurerAdapter {
		@Autowired
		private PasswordEncoder passwordEncoder;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("system").password(passwordEncoder.encode("1234")).roles("ADMIN");
		}	
		
		// 파라미터 http는 스프링 시큐리티를 이용한 접근 통제 정보를 저장할 객체
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// /admin/** 경로 중 접근 통제를 하지 않는 예외 경로를 먼저 설정
			http.authorizeRequests().antMatchers("/admin/login").permitAll();
			
			// /admin/**로 들어오는 요청에 대해 ADMIN 권한과 폼 로그인 설정
			http.requestMatchers().antMatchers("/admin/**").and().authorizeRequests().anyRequest().hasRole("ADMIN")
				.and().formLogin().loginPage("/admin/login").loginProcessingUrl("/admin/login")
				.and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/").invalidateHttpSession(true);
		}
	}
	
	// 관리자가 아니면 @PreAuthorize, @Secured 어노테이션 기반으로 접근 통제
	@Order(2)
	@Configuration //설정파일 or Bean을 등록하기 위한 어노테이션
	@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
	// MethodSecurity용 설정이 따로 필요한데 이때 사용, 속성값들의 기본은 false
	// prePostEnabled : @PreAuthorize, @PostAuthorize(특정 메소드 호출 전,후 권한을 확인) 어노테이션을 사용하여 인가 처리를 하고 싶을 때 사용하는 옵션
	// securedEnabled : @Secured(특정 메소드 호출 전 권한을 확인, 스프링에서 지원) 어노테이션을 사용하여 인가 처리를 하고 싶을 때 사용하는 옵션
	// jsr250Enabled : @RoleAllowed(특정 메소드 호출 전 권한을 확인, 자바에서 지원) 어노테이션을 사용하여 인가 처리를 하고 싶을 때 사용하는 옵션
	public class ConsumerSecurityConfig extends WebSecurityConfigurerAdapter{
		// WebSecurityConfigurerAdapter 이용 불가
		// 이용 방법 : 1. WebSecurityConfigurerAdapter 상속
		// 			 2. configure 메소드 오버라이딩
		// 			 3. SecurityFilterChain을 Bean으로 등록 @EnableWebSecurity를 통한 자동등록
	
		@Autowired
		private DataSource dataSource;
		// 1. DB 서버와의 연결 - Spring JDBC를 이용하려면 DB 커넥션을 가져오는 DataSource를 빈으로 먼저 등록해줘야한다
		//					- JDBC란? DB에 접근할수 있도록 Java에서 제공하는 API (Java Database Connectivity)
		// 2. DB Connection pooling 기능 - 일정량의 Connection객체를 미리 만들어 저장해두었다가 요청시 꺼내쓴다
	
		@Autowired // 로그인에 실패했을 때 처리 -> 로그인 실패횟수 증가, 5회 실패하면 블록
		private ConsumerLoginFailureHandler consumerLoginFailureHandler;
		
		@Autowired
		private ConsumerLoginSuccessHandler consumerLoginSuccessHandler;
		
		@Autowired
		private ConsumerAccessDeniedHandler consumerAccessDeniedHandler;
	
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select C_ID, C_PASSWORD, C_ENABLED from CONSUMER where C_ID=? and rownum<=1")
				.authoritiesByUsernameQuery("select C_ID, C_ROLE from CONSUMER where C_ID=? and rownum<=1");
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.exceptionHandling().accessDeniedHandler(consumerAccessDeniedHandler)
				.and().formLogin().loginPage("/consumer/login").loginProcessingUrl("/consumer/login").usernameParameter("cId").passwordParameter("cPassword").successHandler(consumerLoginSuccessHandler).failureHandler(consumerLoginFailureHandler)
				.and().logout().logoutUrl("/consumer/logout").logoutSuccessUrl("/").invalidateHttpSession(true);
		}
	}
}
