package com.example.demo.seller.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.seller.dao.*;
import com.example.demo.seller.dto.*;
import com.example.demo.seller.entity.*;

@Service
public class SmenuService {

	@Autowired
	private SmenuDao menuDao;
	
	// 메뉴 추가
	public Smenu write(SmenuDto.Write dto, Integer sGroupNum) {
		Smenu sMenu = dto.toEntity().addGroupNum(sGroupNum);
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
