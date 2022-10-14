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
public class SmenuGroupServiceTest {

	@Autowired
	private SmenuGroupService service;
	
//	//@Test
//	public void addTset() {
//		SmenuGroupDto.Write dto = SmenuGroupDto.Write.builder().sGroupName("메뉴").build();
//		SmenuGroup SmenuGroup = service.write(dto,1);
//		assertNotNull(SmenuGroup.getSGroupNum());
//	}
//	
//	//@Test
//	public void updateTest() {
//		SmenuGroupDto.Update dto = SmenuGroupDto.Update.builder().sGroupName("인기메뉴").sGroupNum(5).build();
//		assertEquals(1, service.update(dto));
//	}
//	
//	@Transactional
//	//@Test
//	public void deleteTest() {
//		assertEquals(1, service.delete(5));
//	}
	
	//@Test
	public void listTest() {
		assertNotEquals(0, service.list(1));
	}
	
	@Test
	public void readTest() {
		assertNotEquals(0, service.read(1));
	}
}
