package com.example.demo.seller.controller;

import java.security.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.seller.dto.*;
import com.example.demo.seller.service.*;

@Controller
public class SmenuController {

	@Autowired
	private SmenuService service;
	
	String prin = "test1";
	
	// 메뉴 상세정보 출력
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "seller/menu/read", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> read(Integer sMenuCode) {
		return ResponseEntity.ok(new RestResponse("OK", service.read(sMenuCode), null));
	}
	
	// 메뉴 추가
//	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "seller/menu/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> write(SmenuDto.Write dto, Principal principal) {
		service.write(dto, prin);
		return ResponseEntity.ok(new RestResponse("OK", "추가 작업이 완료되었습니다.", null));
	}
	
	// 메뉴 변경
//	@PreAuthorize("isAuthenticated()")
	@PutMapping(value = "seller/menu/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> update(SmenuDto.Update dto, Principal principal) {
		service.update(dto, prin);
		return ResponseEntity.ok(new RestResponse("OK", "변경이 완료되었습니다.", null));
	}
	
	// 메뉴 삭제
//	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = "seller/menu/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> delete(Integer sMenuCode, Principal principal) {
		service.delete(sMenuCode, prin);
		return ResponseEntity.ok(new RestResponse("OK", "삭제가 완료되었습니다.", null));
	}
	
	// 메뉴 출력
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "seller/menu/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> list(Integer sGroupNum) {
		return ResponseEntity.ok(new RestResponse("OK", service.list(sGroupNum), null));
	}
}
