package com.example.demo.consumer.dao;

import java.util.Optional;


import org.apache.ibatis.annotations.Mapper;

import com.example.demo.consumer.dto.ConsumerDto;
import com.example.demo.consumer.entity.Consumer;

@Mapper
public interface ConsumerDao {
	public Boolean cIdCheck(String cId);
	
	public Boolean cNicknameCheck(String cNickname);
	
	public Boolean cEmailCheck(String cEmail);
	
	public Integer cMemberJoin(Consumer consumer);
	
	public Optional<ConsumerDto.OutputFindId> cFindId(String cEmail);
	
	// public Optional<Consumer> cFindId(String cEmail);
	
	public Optional<Consumer> cFindPassword(String cId, String cEmail);
	
	public Optional<ConsumerDto.Read> cMemberRead(String cId);

	public Integer cMemberUpdate(Consumer consumer);
	
	public Integer cDeleteAccount(String cId);
	
}
