package com.example.demo.seller.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.seller.dto.RestResponse;
import com.example.demo.seller.dto.SstoreDto;
import com.example.demo.seller.dto.SstoreDto.Read;
import com.example.demo.seller.service.SstoreService;

@Validated
@Controller
public class SstoreController {

	@Autowired
	private SstoreService service;
	
	
	@PostMapping(value="/store/new" , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> write( SstoreDto.write dto, MultipartFile logoimg) {
		System.out.println(logoimg);
		service.AddStore(dto, logoimg);
		return ResponseEntity.ok(new RestResponse("OK", "가게 등록 성공", "/"));
	}
	
	@PutMapping(value="/store/update", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> update (SstoreDto.update dto , MultipartFile logoimg) {
		service.UpdateStore(dto,logoimg);
		return ResponseEntity.ok(new RestResponse("OK", "변경이 완료되었습니다.", "/"));
	}
	
	@DeleteMapping(value="/store/delete", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> delete(Integer sStoreNum) {
		service.DeleteStore(sStoreNum);
		return ResponseEntity.ok(new RestResponse("OK", "가게 삭제 성공", "/"));
	}
	
	@GetMapping(value="/store" , produces=MediaType.APPLICATION_JSON_VALUE)
	//public ResponseEntity<Read> read(@ModelAttribute Integer sStoreNum) {
	public ResponseEntity<RestResponse> read(Integer sStoreNum) {
		Optional<Read> store = service.ReadStore(sStoreNum);
		store.get();
		return ResponseEntity.ok(new RestResponse("OK", store, null));
	}
	
	@PostMapping(value="/store/BnumOverlap")
	@ResponseBody
	public Integer Overlap(Integer sStoreBNum) {
		Integer cnt = service.OverlapStoreBnum(sStoreBNum);
		return cnt;
	}
}
