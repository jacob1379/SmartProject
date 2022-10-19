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
	
	@InitBinder
	public void init(WebDataBinder wdb) {
		wdb.registerCustomEditor(LocalDate.class, new MyDatePropertyEditor());
	}
	
	@GetMapping(path="/consumer/check/id", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> idCheck(String cId) {
		service.idCheck(cId);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "사용할 수 있는 아이디입니다", null));
	}
	
	@GetMapping(path="/consumer/check/nickname", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> nicknameCheck(String cNickname) {
		service.nicknameCheck(cNickname);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "사용할 수 있는 닉네임입니다", null));
	}
	
	@GetMapping(path="/consumer/check/email", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> emailCheck(String cEmail) {
		service.emailCheck(cEmail);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "사용할 수 있는 이메일입니다", null));
	}
	
	@PostMapping(value="/consumer/new", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> join(ConsumerDto.Join dto) {
		service.join(dto);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "가입 성공", "/login"));
	}
	
	@GetMapping(path="/consumer/find/id", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> findId(@Valid ConsumerDto.InputFindId dto, BindingResult bindingResult){
		service.cFindId(dto);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "아이디를 이메일로 보냈습니다", null));
	}
	
	@GetMapping(path="/consumer/find/password", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsumerResponseDto> findPassword(@Valid ConsumerDto.FindPassword dto, BindingResult bindingResult) {
		service.cFindPassword(dto);
		return ResponseEntity.ok(new ConsumerResponseDto("OK", "비밀번호를 이메일로 보냈습니다", null));
	}
	
}
