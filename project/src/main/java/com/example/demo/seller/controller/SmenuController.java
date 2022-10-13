package com.example.demo.seller.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.seller.dto.*;
import com.example.demo.seller.dto.SmenuDto.*;
import com.example.demo.seller.entity.*;
import com.example.demo.seller.service.*;

@Controller
public class SmenuController {

	@Autowired
	private SmenuService service;
	
	// 메뉴 상세정보 출력
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "menu/read", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Read> read(Integer sMenuCode) {
		return ResponseEntity.ok(service.read(sMenuCode));
	}
	
	// 메뉴 추가
//	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "menu/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Smenu> write(SmenuDto.Write dto, Integer sGroupNum) {
		return ResponseEntity.ok(service.write(dto, sGroupNum));
	}
	
	// 메뉴 변경
//	@PreAuthorize("isAuthenticated()")
	@PutMapping(value = "menu/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> update(SmenuDto.Update dto) {
		return ResponseEntity.ok(service.update(dto));
	}
	
	// 메뉴 삭제
//	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = "menu/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> delete(Integer sMenuCode) {
		return ResponseEntity.ok(service.delete(sMenuCode));
	}
	
	// 메뉴 출력
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "menu/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ForList>> list(Integer sGroupNum) {
		return ResponseEntity.ok(service.list(sGroupNum));
	}
}
