package com.example.demo.seller.controller;

import java.util.*;

import javax.validation.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.seller.dto.*;
import com.example.demo.seller.dto.SmenuGroupDto.*;
import com.example.demo.seller.entity.*;
import com.example.demo.seller.service.*;

@Controller
public class SmenuGroupController {

	@Autowired
	private SmenuGroupService service;
	
	// 메뉴 그룹 상세정보 출력
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "menu/group", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Read> read(@RequestParam Integer sGroupNum) {
		return ResponseEntity.ok(service.read(sGroupNum));
	}
	
	// 메뉴 그룹 추가
//	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "menu/group/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmenuGroup> write(@ModelAttribute SmenuGroupDto.Write dto, Integer sStoreNum) {
		return ResponseEntity.ok(service.write(dto, sStoreNum));
	}
	
	// 메뉴 그룹 변경
//	@PreAuthorize("isAuthenticated()")
	@PutMapping(value = "menu/group", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> update(@ModelAttribute SmenuGroupDto.Update dto) {
		return ResponseEntity.ok(service.update(dto));
	}
	
	// 메뉴 그룹 삭제
//	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = "menu/group", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> delete(@RequestParam Integer sGroupNum) {
		return ResponseEntity.ok(service.delete(sGroupNum));
	}
	
	// 메뉴 그룹 출력
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "menu/group/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ForList>> list(@RequestParam Integer sStorNum) {
		return ResponseEntity.ok(service.list(sStorNum));
	}
}
