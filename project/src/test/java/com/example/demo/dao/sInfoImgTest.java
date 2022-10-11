package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.seller.dao.SInfoImgDao;
import com.example.demo.seller.entity.SinfoImg;

@SpringBootTest
public class sInfoImgTest {

	@Autowired
	SInfoImgDao dao;
	
	//@Transactional
	//@Test
	public void saveTest() {
		SinfoImg result = SinfoImg.builder().sStoreNum(1).sInfoImg("ddsdd").build();
		assertEquals(1, dao.imgSave(result)); 
	}
	
	//@Test
	public void readTest() {
		System.out.println(dao.imgRead(1).size());
		assertEquals(3, dao.imgRead(1).size());
	}
	
	@Transactional
	@Test
	public void deleteTest() {
		assertEquals(1, dao.imgDelete(1)); 
	}
	
}
