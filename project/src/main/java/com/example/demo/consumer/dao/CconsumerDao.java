package com.example.demo.consumer.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.consumer.dto.CconsumerDto;
import com.example.demo.consumer.entity.Cconsumer;

@Mapper
public interface CconsumerDao {
	// 회원가입
	public Integer cMemberJoin(Cconsumer cconsumer);
	
	// 회원정보 수정
	public Integer cMemberUpdate(Cconsumer cconsumer);
	
	// 회원정보 출력
	public List<CconsumerDto.InfoList> cMemberInfoList(String cId);
	
	// 아이디 중복 검사
	public Integer cIdOverlap(String cId);
	
	// 닉네임 중복 검사
	public Integer cNickOverlap(String cNickname);
	
	// 비밀번호 확인
	public Integer cPasswordCheck(String cPassword);
	
	// 아이디 찾기
	public Optional<CconsumerDto.FindId> cFindId(Integer cPhone);
	
	// 비밀번호 찾기
	public Optional<CconsumerDto.FindPassword> cFindPassword(String cId, Integer cPhone);
	
	// 이메일 인증
	
	// 회원탈퇴
	public Integer cDeleteAccount(Cconsumer cconsumer);
	
}
