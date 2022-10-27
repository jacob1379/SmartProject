package com.example.demo.seller.dto;

import javax.validation.constraints.*;

import org.springframework.web.multipart.*;

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
		private Integer sGroupNum;
		@NotEmpty(message = "메뉴명은 필수입력입니다")
		private String sMenuName;
		private String sMenuInfo;
		@NotNull(message = "메뉴 가격은 필수입력입니다")
		private Integer sMenuPrice;
		
		private MultipartFile sMenuImg;
		
		public Smenu toEntity() {
			return Smenu.builder().sGroupNum(sGroupNum).sMenuName(sMenuName).sMenuInfo(sMenuInfo).sMenuPrice(sMenuPrice).build();
		}
	}
	
	@Data
	@Builder
	public static class Update {
		private Integer sMenuCode;
		private String sMenuName;
		private String sMenuInfo;
		private Integer sMenuPrice;
		
		private MultipartFile sMenuImg;
		
		public Smenu toEntity() {
			return Smenu.builder().sMenuName(sMenuName).sMenuInfo(sMenuInfo).sMenuPrice(sMenuPrice).sMenuCode(sMenuCode).build();
		}
	}
}
