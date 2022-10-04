package com.example.demo.seller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.seller.dao.SstoreDao;
import com.example.demo.seller.dto.SstoreDto;
import com.example.demo.seller.entity.Sstore;

@Service
public class SstoreService {

	@Autowired
	SstoreDao storedao;
	
	
	public Sstore AddStore(SstoreDto.write dto) {
		Sstore store = dto.toEntity();
		storedao.StoreAdd(store);
		return store;
	}
}
