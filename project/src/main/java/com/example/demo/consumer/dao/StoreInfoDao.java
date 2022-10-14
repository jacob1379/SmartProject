package com.example.demo.consumer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.consumer.dto.StoreInfoDto;

@Mapper
public interface StoreInfoDao {

	// 가게 리스트 출력
	public List<StoreInfoDto.StoreListDto> storelist(Integer sCategoryNum, Integer sLocationCode);
	// 가게 정보 출력
	
	// 메뉴 리스트 출력
	
	// 메뉴 상세 정보 출력
	
	// 가게 리뷰 출력
	
	// 카테고리 리스트 읽어오기
	public List<StoreInfoDto.ReadCategoryDto> foodCategoryRead();
	//
}
