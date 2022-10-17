package com.example.demo.consumer.dto;

import java.util.List;

import lombok.Data;

public class StoreInfoDto {

	@Data
	public static class ReadCategoryDto {
		private Integer sCategoryNum;
		private String  sCategoryName;
	}
	
	@Data
	public static class StoreListDto {
		private String sStoreName;
		private Integer sMinDelevery;
		private String sStroeLogo;
		private Integer sStoreNum;
	}
	
	@Data
	public static class StoreInfoDetailDto {
		private String sGroupName;
		private Integer sGroupNum;
	}
	
	@Data
	public static class StoreInfoMenuListDto { 
		private Integer sGroupNum;
		private Integer sMenuCode;
		private String sMenuName;
		private String sMenuInfo;
		private String sMenuImg;
		private Integer sMenuPrice;
	}
	
	@Data
	public static class menuDetailDto {
		private Integer sStoreNum;
		private Integer sGroupNum;
		private Integer sMenuCode;
		private String sMenuName;
		private String sMenuInfo;
		private String sMenuImg;
		private Integer sMenuPrice;
	}
}
