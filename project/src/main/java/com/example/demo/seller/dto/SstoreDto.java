package com.example.demo.seller.dto;

import lombok.AccessLevel;
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
}
