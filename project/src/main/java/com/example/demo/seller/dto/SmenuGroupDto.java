package com.example.demo.seller.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SmenuGroupDto {

	@Data
	public static class ForList{		
		private Integer sGroupNum;
		private String sGroupName;
		private Integer sStoreNum;
	}
	
	@Data
	public static class Read{		
		private Integer sGroupNum;
		private String sGroupName;
		private Integer sStoreNum;
	}
}
