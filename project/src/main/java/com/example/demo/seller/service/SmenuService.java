package com.example.demo.seller.service;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.example.demo.seller.dao.*;
import com.example.demo.seller.dto.*;
import com.example.demo.seller.entity.*;

@Service
public class SmenuService {

	@Autowired
	private SmenuDao menuDao;
	@Value("c:/upload/profile")
	private String profileFolder;
	@Value("http://localhost:8087/profile/")
	private String profilePath;
	
	// 메뉴 추가
	public Smenu write(SmenuDto.Write dto, Integer sGroupNum) {
		Smenu sMenu = dto.toEntity().addGroupNum(sGroupNum);
		MultipartFile profile = dto.getSMenuImg();
		String imgName = System.currentTimeMillis() + "_"+ profile.getOriginalFilename();
		String profileName = "기본이미지.jpg";
		// 프로필 사진이 있으면 저장하고 변경
		if(profile!=null && profile.isEmpty()==false) {
			// 폴더명, 파일명으로 빈 파일을 생성한다
			File file = new File(profileFolder, imgName);
			try {
				profileName = imgName;
				profile.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sMenu.addJoinInfo(profileName);
		menuDao.menuAdd(sMenu);
		return sMenu;
	}
	
	// 메뉴 변경
	public Integer update(SmenuDto.Update dto) {
		return menuDao.menuUpdate(dto.toEntity());
	}
	
	// 메뉴 삭제
	public Integer delete(Integer sMenuCode) {
		return menuDao.menuDelete(sMenuCode);
	}
	
	// 메뉴 상세 출력
	public SmenuDto.Read read(Integer sMenuCode) {
		return menuDao.menulnforRead(sMenuCode).get();
	}
	
	// 메뉴 리스트 출력
	public List<SmenuDto.ForList> list(Integer sGroupNum) {
		return menuDao.menuListRead(sGroupNum);
	}
}
