package com.example.demo.seller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.seller.dto.RestResponse;
import com.example.demo.seller.dto.OrderDto.OrderRead;
import com.example.demo.seller.service.SorderService;

@Validated
@Controller
public class SorderController {
	
	@Autowired
	private SorderService service;

	@GetMapping(value ="/store/order", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> listRead(Integer sStoreNum) {
		List<OrderRead> order =  service.orderRead(sStoreNum);
		return ResponseEntity.ok(new RestResponse("OK", order, "/"));
	}
}
