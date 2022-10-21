package com.example.demo.seller.entity;


import com.example.demo.seller.dto.SstoreDto;
import com.example.demo.seller.dto.SstoreDto.Read;

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
	
	public void addStoreInfo(String storeLogoName) {
		this.sStoreLogo = storeLogoName;
	}
	
	public Read toDto() {
		return SstoreDto.Read.builder().sStoreName(sStoreName).sStoreNum(sStoreNum)
				.sStoreAddress(sStoreAddress).sStoreLogo(sStoreLogo).sMinDeleVery(sMinDeleVery)
				.sStoreTime(sStoreTime).sStoreIntro(sStoreIntro).sStoreStatus(sStoreStatus)
				.sStoreReview(sStoreReview).build();
	}
}
