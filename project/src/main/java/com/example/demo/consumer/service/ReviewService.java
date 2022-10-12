package com.example.demo.consumer.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.consumer.dao.AreviewDao;
import com.example.demo.consumer.dto.ReviewDto;
import com.example.demo.consumer.entity.Areview;

@Service
public class ReviewService {

	@Autowired
	AreviewDao revdao;
	
	@Value("c:/upload/profile")
	private String ImgFolder;
	@Value("http://localhost:8087/profile/")
	private String ImgUrl;
	
	public Areview cReviewWrtie(ReviewDto.SaveDto dto) {
		
		Areview areview = dto.toEntity();
		
		MultipartFile foodImg = dto.getAReviewImg();
		
		String imgName = null;
		
		if(foodImg != null && foodImg.isEmpty() == false) {
			
			File file = new File(ImgFolder, foodImg.getOriginalFilename());
			
			try {
				foodImg.transferTo(file);
				imgName = foodImg.getOriginalFilename();
			}
			
			catch(IllegalStateException e) {
				e.printStackTrace();
			}
			
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		areview.addInfo("aa", "spring", imgName, 1); // 회원 정보, 주문번호 불러오는 dao 필요,
		revdao.cReviewWrtie(areview);
		
		return areview; 
	}
	
	public List<ReviewDto.ListDto> cReviewRead(String cId) {
		
		return revdao.cReviewRead(cId);
	}
	
	public String cReviewUpdate(ReviewDto.UpdateDto dto) {
		// 요청자와 리뷰 쓴 사람이 같은지 확인
		revdao.cReviewUpdate(dto);
		
		return "내용이 변경되었습니다";
	}
	
	public String delete(Integer aReviewNum, String cId) {
		// 요청자와 리뷰 쓴 사람이 같은지 확인
		revdao.cReviewDelete(aReviewNum, cId);
		revdao.deleteReply(aReviewNum);
		
		return "리뷰가 삭제되었습니다";
	}
	
}
