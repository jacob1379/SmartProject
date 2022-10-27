package com.example.demo.seller.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.seller.dto.*;
import com.example.demo.seller.service.*;

@Controller
public class SmenuGroupController {

	@Autowired
	private SmenuGroupService service;
	
	// 메뉴 그룹 상세정보 출력
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "seller/menu/group/read", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> read(@RequestParam Integer sGroupNum) {
		return ResponseEntity.ok(new RestResponse("OK", service.read(sGroupNum), null));
	}
	
	// 메뉴 그룹 추가
//	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "seller/menu/group/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> write(@ModelAttribute SmenuGroupDto.Write dto, String sId) {
		service.write(dto, sId);
		return ResponseEntity.ok(new RestResponse("OK", "메뉴 추가 완료", null));
	}
	
	// 메뉴 그룹 변경
//	@PreAuthorize("isAuthenticated()")
	@PutMapping(value = "seller/menu/group/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> update(@ModelAttribute SmenuGroupDto.Update dto, String sId) {
		System.out.println(dto);
		service.update(dto, sId);
		return ResponseEntity.ok(new RestResponse("OK", "메뉴 그룹 변경", null));
	}
	
	// 메뉴 그룹 삭제
//	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = "seller/menu/group/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> delete(@RequestParam Integer sGroupNum, String sId) {
		service.groupnumdelete(sGroupNum);
		service.delete(sGroupNum, sId);
		return ResponseEntity.ok(new RestResponse("OK", "메뉴 그룹 삭제", null));
	}
	
	// 메뉴 그룹 출력
//	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "seller/menu/group/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> list(@RequestParam Integer sStoreNum) {
		return ResponseEntity.ok(new RestResponse("OK", service.list(sStoreNum) , null));
	}
}
