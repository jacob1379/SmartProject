package com.example.demo.seller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.seller.dao.SInfoImgDao;
import com.example.demo.seller.dto.SInfoImgDto;
import com.example.demo.seller.dto.SInfoImgDto.ForList;
import com.example.demo.seller.entity.SinfoImg;

@Service
public class SInfoImgService {
	@Autowired
	SInfoImgDao dao;
	
	public SinfoImg saveImg(SInfoImgDto.save dto) {
		SinfoImg infoImg = dto.toEntity();
		dao.imgSave(infoImg);
		return infoImg;
	}
	
	public Integer deleteImg(Integer sStoreNum) {
		return dao.imgDelete(sStoreNum);
	}
	
	public List<ForList> readImg(Integer sStoreNum) {
		return dao.imgRead(sStoreNum);
	}
}
