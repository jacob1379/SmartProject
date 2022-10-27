package com.example.demo.seller.dto;

import java.util.*;

import javax.validation.constraints.*;

import com.example.demo.seller.entity.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SmenuGroupDto {

	@Data
	public static class ForList{		
		private Integer sGroupNum;
		private String sGroupName;
		private Integer sStoreNum;
		
		private List<SmenuDto.ForList> sMenus;
	}
	
	@Data
	public static class Read{		
		private Integer sGroupNum;
		private String sGroupName;
		private Integer sStoreNum;
		
		private List<SmenuDto.ForList> sMenus;
	}
	
	@Data
	@Builder
	public static class Write {
		@NotEmpty(message = "메뉴그룹명은 필수입력입니다.")
		private String sGroupName;
		private Integer sStoreNum;

		public SmenuGroup toEntity() {
			return SmenuGroup.builder().sGroupName(sGroupName).sStoreNum(sStoreNum).build();
		}
	}
	
	@Data
	@Builder
	public static class Update {

		private Integer sGroupNum;
		private String sGroupName;
		
		public SmenuGroup toEntity() {
			return SmenuGroup.builder().sGroupNum(sGroupNum).sGroupName(sGroupName).build();
		}
	}
	
	@Data
	public static class Delete {
		private Integer sGroupNum;
		
		public SmenuGroup toEntity() {
			return SmenuGroup.builder().sGroupNum(sGroupNum).build();
		}
	}
}
