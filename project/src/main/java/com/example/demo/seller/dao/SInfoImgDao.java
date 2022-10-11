package com.example.demo.seller.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.seller.dto.SInfoImgDto;
import com.example.demo.seller.entity.SinfoImg;

@Mapper
public interface SInfoImgDao {

	public Integer imgSave(SinfoImg infoImg);
	
	public List<SInfoImgDto.ForList> imgRead(Integer sStoreNum);
	
	public Integer imgDelete(Integer sImgNum);
}
