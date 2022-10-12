package com.example.demo.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.consumer.dto.BasketDto;
import com.example.demo.consumer.dto.ResponseDto;
import com.example.demo.consumer.service.BasketService;

@RestController
public class BasketController {
	
	@Autowired
	BasketService service;
	
	//@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/consumer/basket/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> save(BasketDto.Save dto) {			
		
		return ResponseEntity.ok(new ResponseDto(service.cBasketAdd(dto),service.cBasketListRead(dto.getCId())));
	}
	
	//@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/consumer/basket/listread", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> list(String cId){ // 로그인 아이디로 대체
		return ResponseEntity.ok(new ResponseDto("장바구니 리스트 출력", service.cBasketListRead(cId)));
	}
	
	//@PreAuthorize("isAuthenticated()")
	@PatchMapping(value = "/consumer/basket/countupdate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> update(BasketDto.CouontUpdate dto){
		
		return ResponseEntity.ok(new ResponseDto(service.UpdateCount(dto), service.cBasketListRead(dto.getCId())));
	}
	
	//@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = "/consumer/basket/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> delete(String cId, Integer cBasketNum){
		service.Delete(cId, cBasketNum);
		return ResponseEntity.ok(new ResponseDto("상품이 삭제되었습니다", service.cBasketListRead(cId)));
	}
}
