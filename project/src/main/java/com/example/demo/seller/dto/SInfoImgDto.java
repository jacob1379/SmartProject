package com.example.demo.seller.dto;

import com.example.demo.seller.entity.SinfoImg;
import com.example.demo.seller.entity.Sstore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class SInfoImgDto {

	
	@Data
	public static class ForList {
		private String sInfoImg;
	}
	

	@Data
	@Builder
	public static class save {
		private Integer sImgNum;
		private Integer sStoreNum;
		private String sInfoImg;
		
		public SinfoImg toEntity() {
			return SinfoImg.builder().sImgNum(sImgNum).sInfoImg(sInfoImg).sStoreNum(sStoreNum).build();
			
		}
	}
}
