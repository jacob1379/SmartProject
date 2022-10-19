package com.example.demo.consumer.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
};
