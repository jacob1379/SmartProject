package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.seller.dao.*;
import com.example.demo.seller.entity.*;

@SpringBootTest
public class SmenuGroupDaoTest {
	@Autowired
	private SmenuGroupDao dao;
	
	//@Test
	public void init() {
		assertNotNull(dao);
	}
	
	//@Test
	public void save() {
		SmenuGroup sMenuGroup = SmenuGroup.builder().sGroupName("세트메뉴").sStoreNum(1).build();
		assertEquals(1, dao.menuGroupAdd(sMenuGroup));
	}
	
	//@Test
	public void read() {
		assertEquals(true, dao.menuGroupRead(1).isPresent());
		assertEquals(true, dao.menuGroupRead(100).isEmpty());
	}
	
	@Test
	public void update() {
		SmenuGroup sMenuGroup = SmenuGroup.builder().sGroupNum(3).sGroupName("사이드메뉴").build();
		assertEquals(1, dao.menuGroupUpdate(sMenuGroup));
	}
	
	@Transactional
	//@Test
	public void delete() {
		assertEquals(1, dao.menuGroupDelete(1));
	}
	
	@Test
	public void ForList() {
		assertNotEquals(0, dao.menuGroupList(1));
	}
}
