package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.seller.dao.*;
import com.example.demo.seller.entity.*;

@SpringBootTest
public class SmenuDaoTest {
	@Autowired
	private SmenuDao dao;
	
	//@Test
	public void save() {
		Smenu sMenu = Smenu.builder().sGroupNum(1).sMenuImg("없음").sMenuInfo("치킨 먹고싶다").sMenuName("양념치킨").sMenuPrice(16000).build();
		assertEquals(1, dao.menuAdd(sMenu));
	}
	
	//@Test
	public void read() {
		assertEquals(true, dao.menulnforRead(1).isPresent());
		assertEquals(true, dao.menulnforRead(100).isEmpty());
	}
	
	//@Test
	public void update() {
		assertEquals(1, dao.menuUpdate(Smenu.builder().sMenuCode(1).sMenuName("후라이드 치킨").build()));
		assertEquals(1, dao.menuUpdate(Smenu.builder().sMenuCode(1).sMenuImg("이미지 뭐죠").build()));
		assertEquals(1, dao.menuUpdate(Smenu.builder().sMenuCode(1).sMenuInfo("설명이 필요없다").build()));
		assertEquals(1, dao.menuUpdate(Smenu.builder().sMenuCode(1).sMenuPrice(17000).build()));		
	}
	
	@Transactional
	//@Test
	public void delete() {
		assertEquals(1, dao.menuDelete(1));
	}
	
	@Test
	public void forList() {
		assertNotEquals(0, dao.menuListRead(1));
	}
}
