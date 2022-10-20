package com.example.demo.consumer.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.consumer.dto.ConsumerDto;
import com.example.demo.consumer.dto.ConsumerResponseDto;
import com.example.demo.consumer.editor.MyDatePropertyEditor;
import com.example.demo.consumer.service.ConsumerService;

@Validated
@Controller
public class ConsumerController {
	@Autowired
	private ConsumerService service;
	
	// 1. 데이터 입출력 방식을 지정
	//		a) 스프링 입력 : 프로퍼티 에디터에 작성
	//		b) 스프링 출력 : 메시지 컨버터, JSON의 경우 jackson 어노테이션
	//		c) 마이바티스 입출력 : TypeHandler
	
	@InitBinder
	public void init(WebDataBinder wdb) {
		wdb.registerCustomEditor(LocalDate.class, new MyDatePropertyEditor());
	}
	// 아이디 중복검사
	@GetMapping(path="/consumer/check/id", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> idCheck(String cId) {
		service.idCheck(cId);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "사용할 수 있는 아이디입니다", null));
	}
	// 닉네임 중복검사
	@GetMapping(path="/consumer/check/nickname", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> nicknameCheck(String cNickname) {
		service.nicknameCheck(cNickname);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "사용할 수 있는 닉네임입니다", null));
	}
	// 이메일 중복검사
	@GetMapping(path="/consumer/check/email", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> emailCheck(String cEmail) {
		service.emailCheck(cEmail);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "사용할 수 있는 이메일입니다", null));
	}
	// 회원가입
	@PostMapping(value="/consumer/new", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> join(ConsumerDto.Join dto) {
		service.join(dto);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "가입 성공", "/login"));
	}
	// 아이디 찾기
	@GetMapping(path="/consumer/find/id", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> findId(@Valid ConsumerDto.FindId dto, BindingResult bindingResult){
		service.cFindId(dto);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "아이디를 이메일로 보냈습니다", null));
	}
	// 비밀번호 찾기
	@GetMapping(path="/consumer/find/password", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> findPassword(@Valid ConsumerDto.FindPassword dto, BindingResult bindingResult) {
		service.cFindPassword(dto);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "비밀번호를 이메일로 보냈습니다", null));
	}
	// 회원정보 보기
	//@PreAuthorize("isAuthentication()")
	@GetMapping(path="/consumer", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> read(String cId) {
		ConsumerDto.Read dto = service.read(cId);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", dto, null));
	}
	// 회원정보 변경
	@PostMapping(path="/consumer", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> update(@Valid ConsumerDto.Update dto, BindingResult bindingResult) {
		service.update(dto,dto.getCId());
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "정보를 변경하였습니다", "/consumer/read"));
	}
}
