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
	
	@Transactional
	//@Test
	public void addStoreTest() {
		SstoreDto.write dto = SstoreDto.write.builder().sStoreName("제목")
				.sStoreAddress("주소").sStoreLogo("이미지").sMinDeleVery(1000)
				.sStoreTime(1200).sStoreIntro("소개").build();
		Sstore store = service.AddStore(dto);
		System.out.println(store);
			
	}
	
	//@Test
	public void updateStoreTest() {
		SstoreDto.update dtos = SstoreDto.update.builder().sStoreNum(2).sStoreStatus(1).sMinDeleVery(13000).sStoreIntro("하이루").sStoreTime(1000).sStoreAddress("동네어딘가").build();
		assertEquals(1, service.UpdateStore(dtos));	
	}
	
	@Transactional
	//@Test
	public void deleteStoreTest() {
		service.DeleteStore(3);
	}
	
	@Test
	public void readStoreTest() {
		System.out.println(service.ReadStore(1));
	}
}
