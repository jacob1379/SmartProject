package com.example.demo.consumer.entity;

import java.time.LocalDate;

import com.example.demo.consumer.dto.ConsumerDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 생성
public class Consumer {
	private String cId;
	private String cPassword;
	private String cNickname;
	private String cName;
	private LocalDate cBirthday;
	private LocalDate cJoinday;
	private String cPhone;
	private String cEmail;
	private String checkcode;
	private String cRole;
	private Integer cLoginFailCount;
	private Boolean cEnabled;
	private Integer cBuyCount;
	private Integer cBuyMoney;
	private String cProfile;
	private Levels cLevel;
	
	public void addJoinInfo(String ProfileName, String checkcode, String encodedPassword, Levels cLevel) {
		this.cProfile = ProfileName;
		this.checkcode = checkcode;
		this.cPassword = encodedPassword;
		this.cLevel = cLevel;
	}
	
	public ConsumerDto.Read toRead() {
		return ConsumerDto.Read.builder().cId(cId).cNickname(cNickname).cName(cName).cBirthday(cBirthday).cJoinday(cJoinday).cPhone(cPhone)
				.cEmail(cEmail).cProfile(cProfile).cBuyCount(cBuyCount).cBuyMoney(cBuyMoney).cLevel(cLevel).build();
	}
	
};
