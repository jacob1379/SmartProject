package com.example.demo.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.consumer.dto.OrderDto;
import com.example.demo.consumer.dto.OrderDto.OrderReadDto;
import com.example.demo.consumer.dto.ResponseDto;
import com.example.demo.consumer.service.OrderService;


@Controller
public class OrderController {

	@Autowired
	OrderService service;
	
	@PostMapping(value = "/consumer/order/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> order(OrderDto.OrderSave orderdto) {
		System.out.println(orderdto);
		return ResponseEntity.ok(new ResponseDto("상품 주문 완료 입니다", service.cOrder(orderdto)));
	}
	
	@GetMapping(value = "/consumer/order/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> read(OrderReadDto dto) {
		
		return ResponseEntity.ok(new ResponseDto("주문신청 내역 출력", service.cOrderListRead(dto)));
	}
	
	@GetMapping(value = "/consumer/order/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> listAll(String cId) {
		
		return ResponseEntity.ok(new ResponseDto("주문내역 출력", service.OrderListAll(cId)));
	}
	
	@DeleteMapping(value = "/consumer/order/cansle", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> orderCansle(Integer aOrderNum, String cId) {
		
		return ResponseEntity.ok(new ResponseDto(service.OrderCansle(aOrderNum, cId), null));
	
	}
	
	@GetMapping(value = "/consumer/order/numcheck", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> findOrderNum(Integer aOrderNum, String cId){

		return ResponseEntity.ok(new ResponseDto("확인 결과", service.findOrderNum(aOrderNum, cId)));
	}
}
