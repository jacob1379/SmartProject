package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.consumer.dao.ConsumerDao;
import com.example.demo.consumer.entity.Consumer;
import com.example.demo.consumer.entity.Levels;

@SpringBootTest
public class ConsumerDaoTest {
	@Autowired
	private ConsumerDao dao;
	
	//@Test
	public void cIdCheck() {
		assertEquals(true, dao.cIdCheck("SPRING123"));
		assertEquals(false, dao.cIdCheck("SUMMER"));
	}
	
	//@Test
	public void cNicknameCheck() {
		assertEquals(true, dao.cNicknameCheck("JustLikeThat"));
		assertEquals(false, dao.cNicknameCheck("MK"));
	}
	
	//@Test
	public void cEmailCheck() {
		assertEquals(true, dao.cEmailCheck("jlt@naver.com"));
		assertEquals(false, dao.cEmailCheck("mk@naver.com"));
	}
	
	@Test
	public void cMemberJoinTest() {
		Consumer consumer = Consumer.builder().cId("SPRING123").cPassword("1234").cName("저라뎃").cNickname("JustLikeThat").cBirthday(LocalDate.of(2022, 10, 6)).cPhone("01013791004").cEmail("jlt@naver.com").cProfile("aa.jpg").cLevel(Levels.GOLD).build();
		assertEquals(1, dao.cMemberJoin(consumer));
	}
	
	//@Test
	public void cFindId() {
		assertNotNull(dao.cFindId("jlt@naver.com").get());
	}
	
	//@Test
	public void cFindPassword() {
		assertNotNull(dao.cFindPassword("SPRING123").get());
	}
	
	//@Test
	public void cMemberReadTest() {
		assertNotNull(dao.cMemberRead("SPRING123").get());
	}
	
	//@Test
	public void cMemberUpdateTest() {
		assertEquals(1, dao.cMemberUpdate(Consumer.builder().cId("SPRING123").cNickname("Rayban").build()));
	}

	@Transactional
	//@Test
	public void cDeleteAccount() {
		assertEquals(1, dao.cDeleteAccount("SPRING123"));
	}
}
