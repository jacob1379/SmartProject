package com.example.demo.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.seller.dao.SorderDao;

@SpringBootTest
public class AAATest {
	@Autowired
	private SorderDao dao;
	
	@Test
	public void test1 ( ) {
		System.out.println(dao.orderListRead(1));
		System.out.println("==================");
	}
	
}
