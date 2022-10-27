package com.example.demo.seller.service;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.*;
import com.example.demo.seller.dao.*;
import com.example.demo.seller.dto.*;
import com.example.demo.seller.entity.*;
import com.example.demo.seller.exception.*;

@Service
public class SmenuService {

	@Autowired
	private SmenuDao menuDao;
	
    @Autowired
    private AmazonS3Client amazonS3Client;
	
	@Value("iciasmartframe2/upload/seller/menuimg")
	private String S3Bucket;
	
	
	// 메뉴 추가
	public void write(SmenuDto.Write dto, String sId) {
//		System.out.println(dto);
		Smenu sMenu = dto.toEntity();
		String profileName = "기본이미지.jpg";
		MultipartFile profile = dto.getSMenuImg();
		
		String id = menuDao.findById(dto.getSGroupNum(), sId).orElseThrow(()-> new SmenuGroupNotFoundException());
		
		if(id.equals(sId)==false)
			throw new JobFailException("추가 권한이 없습니다.");
		
		// 프로필 사진이 있으면 저장하고 변경
		if(profile!=null && profile.isEmpty()==false) {
			// 폴더명, 파일명으로 빈 파일을 생성한다
//			File file = new File(S3Bucket, imgName );
			try {
				
				profileName = System.currentTimeMillis() + "_"+ profile.getOriginalFilename();
				
                long size = profile.getSize(); // 파일 크기
                
                ObjectMetadata objectMetaData = new ObjectMetadata();
                objectMetaData.setContentType(profile.getContentType());
                objectMetaData.setContentLength(size);
                
                // S3에 업로드
                amazonS3Client.putObject(
                    new PutObjectRequest(S3Bucket, profileName, profile.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
                );
                
                String imagePath = amazonS3Client.getUrl(S3Bucket, profileName).toString();
                System.out.println(imagePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sMenu.addJoinInfo(profileName);
		menuDao.menuAdd(sMenu);
	}
	
	// 메뉴 변경
	public void update(SmenuDto.Update dto, String sId) {
		Smenu sMenu = dto.toEntity(); 
		String profileName = "기본이미지.jpg";
		MultipartFile profile = dto.getSMenuImg();
		
		String id = menuDao.findById(dto.getSMenuCode(), sId).orElseThrow(()-> new SmenuGroupNotFoundException());
		
		if(id.equals(sId)==false)
			throw new JobFailException("변경 권한이 없습니다.");
		
		// 프로필 사진이 있으면 저장하고 변경
		if(profile!=null && profile.isEmpty()==false) {
			// 폴더명, 파일명으로 빈 파일을 생성한다
//			File file = new File(S3Bucket, imgName );
			try {
				
				profileName = System.currentTimeMillis() + "_"+ profile.getOriginalFilename();
				
                long size = profile.getSize(); // 파일 크기
                
                ObjectMetadata objectMetaData = new ObjectMetadata();
                objectMetaData.setContentType(profile.getContentType());
                objectMetaData.setContentLength(size);
                
                // S3에 업로드
                amazonS3Client.putObject(
                    new PutObjectRequest(S3Bucket, profileName, profile.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
                );
                
                String imagePath = amazonS3Client.getUrl(S3Bucket, profileName).toString();
                System.out.println(imagePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sMenu.addJoinInfo(profileName);
		menuDao.menuUpdate(sMenu);
	}
	
	// 메뉴 삭제
	public Integer delete(Integer sMenuCode, String sId) {
		
		String id = menuDao.findById(sMenuCode, sId).orElseThrow(()-> new SmenuNotFoundException());
		
		if(id.equals(sId)==false)
			throw new JobFailException("작업 권한이 없습니다.");
		
		return menuDao.menuDelete(sMenuCode);
		
	}
	
	// 메뉴 상세 출력
	public SmenuDto.Read read(Integer sMenuCode) {
		return menuDao.menulnforRead(sMenuCode).get();
	}
	
	// 메뉴 리스트 출력
	public List<SmenuDto.ForList> list(Integer sGroupNum) {
		return menuDao.menuListRead(sGroupNum);
	}
}
	