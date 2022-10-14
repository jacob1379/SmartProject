package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.seller.dto.SstoreDto;
import com.example.demo.seller.entity.Sstore;
import com.example.demo.seller.service.SstoreService;

@SpringBootTest
public class SstoreServiceTest {

	@Autowired
	private SstoreService service;
	
}
