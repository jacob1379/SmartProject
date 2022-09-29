package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.catalina.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.seller.dao.SstoreDao;
import com.example.demo.seller.entity.Sstore;

@SpringBootTest
public class SstoreDaoTest {

	@Autowired
	SstoreDao sStoreDao;
	
	//@Transactional
	@Test
	public void StoreAddTest() {
		Sstore store = Sstore.builder().sStoreName("김밥천국").sStoreAddress("학익동3-1")
				.sMinDeleVery(20000).sStoreTime(1200).sStoreIntro("종합분식 김밥천국입니다").build();
		assertEquals(1, sStoreDao.StoreAdd(store));
	}
	
}
