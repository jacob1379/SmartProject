package com.example.demo.seller.entity;

import lombok.*;

@Getter
@Builder
@ToString
public class Smenu {
	private Integer sGroupNum;
	private Integer sMenuCode;
	private String sMenuName;
	private String sMenuInfo;
	private String sMenuImg;
	private Integer sMenuPrice;
}
