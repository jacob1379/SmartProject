package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.seller.dto.*;
import com.example.demo.seller.entity.*;
import com.example.demo.seller.service.*;

@SpringBootTest
public class SmenuServiceTest {
	
	@Autowired
	private SmenuService service;
	
	//@Test
	public void save() {
		SmenuDto.Write dto = SmenuDto.Write.builder().sMenuName("간장치킨").sMenuInfo("짭짤한 맛").sMenuPrice(18000).build();
		Smenu sMenu = service.write(dto,1);
		assertNotNull(sMenu.getSMenuCode());
	}
	
	@Transactional
	//@Test
	public void updateTest() {
		SmenuDto.Update dto = SmenuDto.Update.builder().sMenuName("양념치킨").sMenuCode(3).build();
		SmenuDto.Update dto2 = SmenuDto.Update.builder().sMenuInfo("설명").sMenuCode(3).build();
		SmenuDto.Update dto3 = SmenuDto.Update.builder().sMenuPrice(19000).sMenuCode(3).build();
		assertEquals(1, service.update(dto));
		assertEquals(1, service.update(dto2));
		assertEquals(1, service.update(dto3));
	}
	
	@Transactional
	//@Test
	public void deleteTest() {
		assertEquals(1, service.delete(3));
	}
	
	//@Test
	public void listTest() {
		assertNotEquals(0, service.list(1));
	}
	
	@Test
	public void readTest() {
		assertNotEquals(0, service.read(1));
	}
}
