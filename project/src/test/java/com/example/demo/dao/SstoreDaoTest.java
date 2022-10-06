package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
	//@Test
	public void StoreAddTest() {
		Sstore store = Sstore.builder().sStoreName("김밥천국2").sStoreAddress("학익동3-1")
				.sMinDeleVery(20000).sStoreTime(1200).sStoreIntro("종합분식 김밥천국입니다").build();
		assertEquals(1, sStoreDao.StoreAdd(store));
		System.out.println(store);
	}
	
	//@Test
	public void StoreUpdateTest() {
		assertEquals(1,sStoreDao.StoreUpdate(Sstore.builder().sStoreNum(1).sStoreName("부뚜막애는순두부").build()));
	}
	
	@Transactional
	//@Test
	public void StoreDeleteTest() {
		sStoreDao.StoreDelete(3);
	}
	
	@Test
	public void storeReadTest() {
		assertNotNull(sStoreDao.StoreRead(1).get());
		//assertNull(sStoreDao.StoreRead(3).get());
		}
	
}
