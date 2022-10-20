package com.example.demo.seller.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.seller.dto.RestResponse;
import com.example.demo.seller.dto.SInfoImgDto;
import com.example.demo.seller.dto.SInfoImgDto.ForList;
import com.example.demo.seller.entity.SinfoImg;
import com.example.demo.seller.service.SInfoImgService;

@Controller
public class SInfoImgController {
	@Autowired
	private SInfoImgService service;
	
	@PostMapping(value="/img/save")
	public ResponseEntity<RestResponse> save(SInfoImgDto.save dto) {
	return ResponseEntity.ok(new RestResponse("OK", dto, null));
	}
	
	@GetMapping(value="/img")
	public ResponseEntity<ForList> ForList(@ModelAttribute Integer sStoreNum) {
		List<SInfoImgDto.ForList> img = service.readImg(sStoreNum);
		return ResponseEntity.ok(img.get(sStoreNum));
	}
	
	@DeleteMapping(value="/img/delete")
	public ResponseEntity<Integer> delete(@ModelAttribute Integer sStoreNum) {
		return ResponseEntity.ok(service.deleteImg(sStoreNum));
	}
}
