package com.example.demo.seller.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.seller.dto.SsellerDto;
import com.example.demo.seller.entity.Sseller;

@Mapper
public interface SsellerDao {
	
	// 판매자 회원 등록
	public Integer SellerSave(Sseller seller);
	
	// 판매자 회원 수정
	public Integer SellerUpdate(Sseller seller);
	
	// 판매자 회원 조회
	public Optional<SsellerDto.Read> SellerRead(String sId);
	
	// 판매자 회원 탈퇴
	public Integer SellerDelete(String sId);
	
	// 사업자번호 중복 확인
	public Boolean SellerBusinessNumOverlap (String sBusinessNum);
	
	// 아이디 중복 확인
	public Boolean SellerExistsById(String sId);
	
	// 이메일 중복 확인
	public Boolean SellerExistsByEmail(String sEmail);
	
	// 이메일로 아이디 찾기
	public Optional<Sseller> sFindByEmail(String sEmail);
	
	// 특정 아이디의 비밀번호 변경
	public Optional<Sseller> stPassword(String sId);
	
	// 비밀번호 초기화
	public Optional<Sseller> scPassword(String sId);
	
	public Optional<Sseller> sFindId(String sId);
	
	
	public Optional<Sseller> sFindCheckcode(String sCheckcode);
	
	public List<String> sFindCheckcodeIsNotEmpty();
	
	public Integer sNotCheckId(List<String> sId);
	
	public Integer sDeleteId(String sId);
}
