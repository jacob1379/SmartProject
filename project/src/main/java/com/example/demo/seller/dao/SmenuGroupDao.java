package com.example.demo.seller.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.seller.dto.*;
import com.example.demo.seller.entity.*;

@Mapper
public interface SmenuGroupDao {
	
	// 메뉴 그룹 추가
	public Integer menuGroupAdd(SmenuGroup sMenuGroup);
	
	// 메뉴 그룹 수정
	public Integer menuGroupUpdate(SmenuGroup sMenuGroup);
	
	// 메뉴 그룹 삭제
	public Integer menuGroupDelete(Integer sGroupNum);
	
	// 메뉴 그룹 1개 출력
	public Optional<SmenuGroupDto.Read> menuGroupRead(Integer sGroupNum);
	
	// 메뉴 그룹 리스트 출력
	public List<SmenuGroupDto.ForList> menuGroupList(Integer sStoreNum);
}
 