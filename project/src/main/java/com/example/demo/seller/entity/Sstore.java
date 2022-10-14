package com.example.demo.seller.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sstore {
	
	
	private Integer sStoreNum;
	private String sStoreName;
	private String sStoreAddress;
	private String sStoreLogo;
	private Integer sMinDeleVery;
	private Integer sStoreTime;
	private String sStoreIntro;
	private Integer sStoreStatus;
	private String sStoreReview;
	private Integer sCategoryNum;
	private Integer sLocationCode;
	private Integer sStoreBNum;
	
	public void addStoreInfo(String storeLogoName) {
		this.sStoreLogo = storeLogoName;
	}
	
	
}
