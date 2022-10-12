package com.example.demo.seller.dto;

import javax.validation.constraints.*;

import com.example.demo.seller.entity.*;

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

	@Data
	@Builder
	public static class Write {
		@NotEmpty(message = "메뉴명은 필수입력입니다")
		private String sMenuName;
		private String sMenuInfo;
		private String sMenuImg;
		@NotNull(message = "메뉴 가격은 필수입력입니다")
		private Integer sMenuPrice;
		
		public Smenu toEntity() {
			return Smenu.builder().sMenuName(sMenuName).sMenuInfo(sMenuInfo).sMenuImg(sMenuImg).sMenuPrice(sMenuPrice).build();
		}		
	}
	
	@Data
	@Builder
	public static class Update {
		private Integer sMenuCode;
		private String sMenuName;
		private String sMenuInfo;
		private String sMenuImg;
		private Integer sMenuPrice;
		public Smenu toEntity() {
			return Smenu.builder().sMenuName(sMenuName).sMenuInfo(sMenuInfo).sMenuImg(sMenuImg).sMenuPrice(sMenuPrice).sMenuCode(sMenuCode).build();
		}
	}
}
