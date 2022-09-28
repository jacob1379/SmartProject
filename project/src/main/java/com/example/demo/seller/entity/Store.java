package com.example.demo.seller.entity;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
public class Store {
	
	private Integer sStoreNum;
	private String sStoreName;
	private String sStoreAddress;
	private String sStoreLogo;
	private Integer sMinDeleVery;
	private Date sStoreTime;
	private String sStoreIntro;
	private Integer sStoreStatus;
	private String sStoreReview;
	private Integer sCategoryNum;
	private Integer sLocationCode;
	
}
