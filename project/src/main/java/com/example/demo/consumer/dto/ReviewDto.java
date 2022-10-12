package com.example.demo.consumer.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.consumer.entity.Areview;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDto {

	@Data
	public static class SaveDto {
		private String aReview;
		private Integer aScore;
		private MultipartFile aReviewImg;
		
		public Areview toEntity() {
			return Areview.builder().aReview(aReview).aScore(aScore).build();
		}
	}
	
	@Data
	public static class ListDto {
		private String aReview;
		private String aNickname;
		private LocalDate aWriteDate;
		private Integer aScore;
		private String aReviewImg;
		
		private String aReplyContent;
		private LocalDate aReplyDate;
		
		private List<String> sMenuName;
	}
	
	@Data
	@Builder
	public static class UpdateDto {
		private Integer aReviewNum;
		private String aReview;
		private String cId;
		
		public Areview toEntity() {
			return Areview.builder().aReviewNum(aReviewNum).aReview(aReview).cId(cId).build();
		}
	}
	
}
