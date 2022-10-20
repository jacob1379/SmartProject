package com.example.demo.seller.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.seller.dao.SInfoImgDao;
import com.example.demo.seller.dto.SInfoImgDto;
import com.example.demo.seller.dto.SInfoImgDto.ForList;
import com.example.demo.seller.entity.SinfoImg;

@Service
public class SInfoImgService {
	@Autowired
	SInfoImgDao dao;
	@Value("/Users/uhzza/ff")
	private String LogoFolder;
	@Value("http://localhost:8080/LogoImg/")
	private String profilePath;
	
	public Integer saveImg(SInfoImgDto.save dto, MultipartFile image) {
		
		SinfoImg infoImg = dto.toEntity();
		File file = new File(LogoFolder, image.getOriginalFilename());
		try {
			image.transferTo(file);
			String imageName = image.getOriginalFilename();
			infoImg.addimg(imageName);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dao.imgSave(infoImg);
	}
	
	public Integer deleteImg(Integer sStoreNum) {
		return dao.imgDelete(sStoreNum);
	}
	
	public List<ForList> readImg(Integer sStoreNum) {
		return dao.imgRead(sStoreNum);
	}
}
