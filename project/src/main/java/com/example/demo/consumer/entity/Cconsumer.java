package com.example.demo.consumer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Cconsumer {
	private String cId;
	private String cPassword;
	private String cNickname;
	private String cName;
	private Integer cBirth;
	private Integer cPhone;
	private String cEmail;
	private Integer cBuyCount;
	private String cProfileImg;
};
