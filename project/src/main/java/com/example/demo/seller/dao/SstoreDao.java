package com.example.demo.seller.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.seller.dto.SstoreDto;
import com.example.demo.seller.entity.Sstore;

@Mapper
public interface SstoreDao {

	//가게 정보 추가
	public Integer StoreAdd(Sstore store);
	
	//가게 정보 수정
	public Integer StoreUpdate(Sstore store);
	
	//가게 정보 출력
	public Optional<SstoreDto.Read> StoreRead(Integer sStoreNum);
	
	//가게 삭제
	public Integer StoreDelete(Integer sStoreNum);
	
}
