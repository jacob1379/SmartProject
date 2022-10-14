package com.example.demo.consumer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.consumer.dao.StoreInfoDao;
import com.example.demo.consumer.dto.StoreInfoDto;

@Service
public class StoreInfoService {

	@Autowired
	StoreInfoDao storeinfo;
	
	public List<StoreInfoDto.ReadCategoryDto> categoryRead(){
		return storeinfo.foodCategoryRead();
	}
	
	public List<StoreInfoDto.StoreListDto> storelist(Integer sCategoryNum, Integer sLocationCode) {
		return storeinfo.storelist(sCategoryNum, sLocationCode);
	}
}
