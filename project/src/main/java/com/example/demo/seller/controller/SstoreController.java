package com.example.demo.seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.seller.dto.SstoreDto;
import com.example.demo.seller.entity.Sstore;
import com.example.demo.seller.service.SstoreService;

@Controller
public class SstoreController {

	@Autowired
	private SstoreService service;
	
	
	@PostMapping(value="/store/new" , produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sstore> write(@ModelAttribute SstoreDto.write dto) {
		return ResponseEntity.ok(service.AddStore(dto));
	}
	
	@PutMapping(value="/store/update", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> update (@ModelAttribute SstoreDto.update dto) {
		return ResponseEntity.ok(service.UpdateStore(dto));
	}
	
	@DeleteMapping(value="/store/delete", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> delete(@ModelAttribute Integer sStoreNum) {
		return ResponseEntity.ok(service.DeleteStore(sStoreNum));
	}
	
	
}
