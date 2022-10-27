package com.example.demo.seller.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.seller.dto.*;
import com.example.demo.seller.entity.*;

@Mapper
public interface SmenuDao {
	
	// 상품 등록
	public Integer menuAdd(Smenu sMenu);
	
	// 상품 수정
	public Integer menuUpdate(Smenu sMenu);
	
	// 상품 삭제
	public Integer menuDelete(Integer sMenuCode);
	
	// 그룹으로 삭제
	public Integer menuGroupDelete(Integer sGroupNum);
	
	// 상품리스트 출력
	public List<SmenuDto.ForList> menuListRead(Integer sGroupNum);
	
	// 상품 상세내역 출력
	public Optional<SmenuDto.Read> menulnforRead(Integer sMenuCode);
	
	// 상품 삭제, 변경 전에 판매회원아이디 확인
	public Optional<String> findById(Integer sStoreNum, String sId);
}
