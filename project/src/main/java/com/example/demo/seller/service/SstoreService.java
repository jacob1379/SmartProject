package com.example.demo.seller.service;

import java.util.List;
import java.util.Optional;

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
	
	public Optional<SstoreDto.Read> ReadStore(Integer sStoreNum) {
		return storedao.StoreRead(sStoreNum);
	}
	
	
	public List<SstoreDto.ForList> list() {
		return storedao.findAll();
	}
	
	public Integer UpdateStore(SstoreDto.update dto) {
		return storedao.StoreUpdate(dto.toEntity());
	}
	
	public Integer DeleteStore(Integer sStoreNum) {
		return storedao.StoreDelete(sStoreNum);
	}
	
	public Integer OverlapStoreBNum(Integer sStoreBNum) {
		Integer Bnum = storedao.StoreBNumOverlap(sStoreBNum);
		return Bnum;
	}
}
