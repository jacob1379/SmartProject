package com.example.demo.seller.entity;

import lombok.*;

@Builder
@ToString
@Getter
public class Smenu {
	private Integer sGroupNum;
	private Integer sMenuCode;
	private String sMenuName;
	private String sMenuInfo;
	private String sMenuImg;
	private Integer sMenuPrice;
	
	public Smenu addGroupNum(Integer sGroupNum) {
		this.sGroupNum = sGroupNum;
		return this;
	}
}
