package com.example.demo.seller.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.seller.dao.*;
import com.example.demo.seller.dto.*;
import com.example.demo.seller.dto.SmenuGroupDto.*;
import com.example.demo.seller.entity.*;
import com.example.demo.seller.exception.*;

@Service
public class SmenuGroupService {

	@Autowired
	private SmenuGroupDao groupDao;
	@Autowired
	private SmenuDao menuDao;
	
	// 그룹 메뉴 쓰기 (메뉴그룹명은 필수입력)
	public SmenuGroup write(SmenuGroupDto.Write dto, Integer sStoreNum) {
		SmenuGroup sMenuGroup = dto.toEntity().addStoreNum(sStoreNum);
		groupDao.menuGroupAdd(sMenuGroup);
		return sMenuGroup;
	}
	
	// 그룹 메뉴 변경
	public Integer update(SmenuGroupDto.Update dto) {
		return groupDao.menuGroupUpdate(dto.toEntity());
	}
	
	// 그룹 메뉴 삭제
	public Integer delete(Integer sGroupNum) {
		return groupDao.menuGroupDelete(sGroupNum);
	}
	
	// 메뉴 그룹 리스트 출력
	public List<ForList> list(Integer sStorNum) {
		return groupDao.menuGroupList(sStorNum);
	}
	
	//map (키 : 값) 매칭
	// 그룹 메뉴 읽기
	public SmenuGroupDto.Read read(Integer sGroupNum) {
		SmenuGroupDto.Read dto = groupDao.menuGroupRead(sGroupNum).orElseThrow(()-> new SmenuGroupNotFoundException());
		dto.setSMenus(menuDao.menuListRead(sGroupNum));
		return dto;
	}
}
