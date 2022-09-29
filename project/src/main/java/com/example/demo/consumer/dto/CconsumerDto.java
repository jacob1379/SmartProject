package com.example.demo.consumer.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class CconsumerDto {
	@Data
	public static class Join {
		private String cId;
		private String cPassword;
		private String cNickname;
		private String cName;
		private Integer cBirth;
		private Integer cPhone;
		private String cEmail;
		private Integer cBuyCount;
		private String cProfileImg;
	}
	@Data
	public static class InfoList {
		
	}
	@Data
	public static class FindId {
		
	}
	@Data
	public static class FindPassword {
		
	}
}
