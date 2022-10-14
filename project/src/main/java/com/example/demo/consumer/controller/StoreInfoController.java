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
}
