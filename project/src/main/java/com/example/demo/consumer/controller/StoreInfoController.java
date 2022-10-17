package com.example.demo.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.consumer.dto.ResponseDto;
import com.example.demo.consumer.service.StoreInfoService;

@RestController
public class StoreInfoController {

	@Autowired
	StoreInfoService service;
	
	@GetMapping(value = "/storeinfo/readcategory", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> readCategory(){
		
		return ResponseEntity.ok(new ResponseDto("카테고리 리스트 출력", service.categoryRead()));
	}
	
	@GetMapping(value = "/storeinfo/readstorelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> storelistread(Integer sCategoryNum, Integer sLocationCode){
		
		return ResponseEntity.ok(new ResponseDto("가게 리스트 출력", service.storelist(sCategoryNum, sLocationCode)));
	}
	
	@GetMapping(value = "/storeinfo/readstoreinfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> storeinforead(Integer sStoreNum, Integer sLocationCode){
		
		return ResponseEntity.ok(new ResponseDto("가게 내용", service.storeinfo(sStoreNum, sLocationCode)));
	}
	
	@GetMapping(value = "/storeinfo/readstoremenu", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> storemenuread(Integer sGroupNum, Integer sLocationCode){
		
		return ResponseEntity.ok(new ResponseDto("메뉴 리스트", service.menulist(sGroupNum, sLocationCode)));
	}
	
	@GetMapping(value = "/storeinfo/menudetail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> storemenuread(Integer sGroupNum, Integer sMenuCode,Integer sLocationCode){
		
		return ResponseEntity.ok(new ResponseDto("메뉴 정보", service.menudetail(sGroupNum, sMenuCode, sLocationCode)));
	}
}
