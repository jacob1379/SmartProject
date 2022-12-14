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
	public SmenuGroup write(SmenuGroupDto.Write dto, String sId) {
		
		String id = menuDao.findById(dto.getSStoreNum(), sId).orElseThrow(()-> new SmenuGroupNotFoundException());
		
		if(id.equals(sId)==false)
			throw new JobFailException("추가 권한이 없습니다.");
		
		SmenuGroup sMenuGroup = dto.toEntity();
		groupDao.menuGroupAdd(sMenuGroup);
		return sMenuGroup;
	}
	
	// 그룹 메뉴 변경
	public Integer update(SmenuGroupDto.Update dto, String sId) {
		
		String id = menuDao.findById(dto.getSGroupNum(), sId).orElseThrow(()-> new SmenuGroupNotFoundException());
		System.out.println(id);
		if(id.equals(sId)==false)
			throw new JobFailException("변경 권한이 없습니다.");
		
		return groupDao.menuGroupUpdate(dto.toEntity());
	}
	
	// 그룹 메뉴 삭제
	public Integer delete(Integer sGroupNum, String sId) {
		
		String id = menuDao.findById(sGroupNum, sId).orElseThrow(()-> new SmenuGroupNotFoundException());
		
		if(id.equals(sId)==false)
			throw new JobFailException("삭제 권한이 없습니다.");
		
		return groupDao.menuGroupDelete(sGroupNum);
	}
	
	public Integer groupnumdelete(Integer sGroupNum) {
		return menuDao.menuGroupDelete(sGroupNum);
	}
	
	// 메뉴 그룹 리스트 출력
	public List<ForList> list(Integer sStoreNum) {
		return groupDao.menuGroupList(sStoreNum);
	}
	
	//map (키 : 값) 매칭
	// 그룹 메뉴 읽기
	public SmenuGroupDto.Read read(Integer sGroupNum) {
		SmenuGroupDto.Read dto = groupDao.menuGroupRead(sGroupNum).orElseThrow(()-> new SmenuGroupNotFoundException());
		dto.setSMenus(menuDao.menuListRead(sGroupNum));
		return dto;
	}
}
