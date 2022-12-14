package com.example.demo.consumer.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.consumer.entity.Consumer;

@Mapper
public interface ConsumerDao {
	// 아이디 중복 검사
	public Boolean cIdCheck(String cId);
	
	// 닉네임 중복 검사
	public Boolean cNicknameCheck(String cNickname);
	
	// 이메일 중복 검사
	public Boolean cEmailCheck(String cEmail);
	
	// 회원가입
	public Integer cMemberJoin(Consumer consumer);
	
	// 아이디 찾기
	public Optional<Consumer> cFindId(String cEmail);
	
	// 비밀번호 찾기
	public Optional<Consumer> cFindPassword(String cId);
	
	// 회원정보 보기
	public Optional<Consumer> cMemberRead(String cId);

	// 회원정보 변경
	public Integer cMemberUpdate(Consumer consumer);
	
	// 회원탈퇴
	public Integer cDeleteAccount(String cId);	
}
