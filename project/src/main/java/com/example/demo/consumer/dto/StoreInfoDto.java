package com.example.demo.consumer.dto;

import lombok.Data;

public class StoreInfoDto {

	@Data
	public static class ReadCategoryDto {
		Integer sCategoryNum;
		String  sCategoryName;
	}
	
	@Data
	public static class StoreListDto {
		String sStoreName;
		Integer sMinDelevery;
		String sStroeLogo;
	}
}
