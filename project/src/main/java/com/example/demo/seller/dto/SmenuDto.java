package com.example.demo.seller.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SmenuDto {
	
	@Data
	public static class ForList {
		private Integer sGroupNum;
		private Integer sMenuCode;
		private String sMenuName;
		private String sMenuInfo;
		private String sMenuImg;
		private Integer sMenuPrice;
	}
	
	@Data
	public static class Read {
		private Integer sMenuCode;
		private String sMenuName;
		private String sMenuInfo;
		private String sMenuImg;
		private Integer sMenuPrice;
	}

}
