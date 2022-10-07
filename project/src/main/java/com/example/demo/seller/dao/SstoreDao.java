package com.example.demo.seller.dao;

import java.util.List;
import java.util.Map;
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
	
	//사업자 번호 중복
	public Integer StoreBNumOverlap(Integer sStoreBNum);
	
	// 가게 리스트 출력
	public List<SstoreDto.ForList> findAll();
	//가게 정보 출력
	public Optional<SstoreDto.Read> StoreRead(Integer sStoreNum);
	//가게 삭제
	public Integer StoreDelete(Integer sStoreNum);
}
