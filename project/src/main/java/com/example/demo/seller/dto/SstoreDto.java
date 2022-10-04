package com.example.demo.seller.dto;

import com.example.demo.seller.entity.Sstore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class SstoreDto {

	
	@Data
	public static class Read {
		private String sStoreName;
		private String sStoreAddress;
		private String sStoreLogo;
		private Integer sMinDeleVery;
		private Integer sStoreTime;
		private String sStoreIntro;
		private Integer sStoreStatus;
		private String sStoreReview;
	}
	
	@Data
	@Builder
	public static class write {
		private String sStoreName;
		private String sStoreAddress;
		private String sStoreLogo;
		private Integer sMinDeleVery;
		private Integer sStoreTime;
		private String sStoreIntro;
		private Integer sStoreStatus;
		private Integer sCategoryNum;
		private Integer sLocationCode;
		
		public Sstore toEntity() {
			return Sstore.builder().sStoreName(sStoreName).sStoreAddress(sStoreAddress)
					.sStoreLogo(sStoreLogo).sMinDeleVery(sMinDeleVery).sStoreTime(sStoreTime)
					.sStoreIntro(sStoreIntro).sStoreStatus(sStoreStatus).sCategoryNum(sCategoryNum)
					.sLocationCode(sLocationCode).build();
		}
	}
}
