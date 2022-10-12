package com.example.demo.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.consumer.dto.ResponseDto;
import com.example.demo.consumer.dto.ReviewDto;
import com.example.demo.consumer.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	ReviewService service;
	
	@PostMapping(value = "/consumer/review/save", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> save (ReviewDto.SaveDto dto) {
		
		return ResponseEntity.ok(new ResponseDto("리뷰작성이 완료되었습니다", service.cReviewWrtie(dto)));
	}
	
	@GetMapping(value = "/consumer/review/read", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> read (String cId) {
		return ResponseEntity.ok(new ResponseDto("사용자의 리뷰 출력", service.cReviewRead(cId)));
	}
	
	
	@PutMapping(value = "/consumer/review/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> update (ReviewDto.UpdateDto dto) {
		return ResponseEntity.ok(new ResponseDto(service.cReviewUpdate(dto), null));
	}
	
	@DeleteMapping(value = "/consumer/review/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> delete (Integer aReviewNum, String cId) {
		return ResponseEntity.ok(new ResponseDto(service.delete(aReviewNum, cId), null));
	}
}
