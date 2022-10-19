package com.example.demo.consumer.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.consumer.dao.ConsumerDao;
import com.example.demo.consumer.dto.ConsumerDto;
import com.example.demo.consumer.entity.Consumer;
import com.example.demo.consumer.entity.Levels;
import com.example.demo.consumer.exception.CounsumerNotFoundException;
import com.example.demo.consumer.exception.JobFailException;
import com.example.demo.consumer.util.MailUtil;


@Service
public class ConsumerService {
	@Autowired
	private ConsumerDao dao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MailUtil mailUtil;
	
	@Value("c:/upload/profile")
	private String profileFolder;
	@Value("http://localhost:8087/profile/")
	private String profilePath;
	
	@PostConstruct
	public void cTest() {
		System.out.println("!!!!!!!!!!");
	}
	
	public void idCheck(String cId) {
		if(dao.cIdCheck(cId))
			throw new JobFailException("사용중인 아이디입니다");
	}
	
	public void nicknameCheck(String cNickname) {
		if(dao.cNicknameCheck(cNickname))
			throw new JobFailException("사용중인 닉네임입니다");
	}
	
	public void emailCheck(String cEmail) {
		if(dao.cEmailCheck(cEmail))
			throw new JobFailException("사용중인 이메일입니다");
	}
	
	public void join(ConsumerDto.Join dto) {
		if(dao.cIdCheck(dto.getCId())) 
			throw new JobFailException("사용중인 아이디입니다");
		if(dao.cNicknameCheck(dto.getCNickname())) 
			throw new JobFailException("사용중인 닉네임입니다");
		if(dao.cEmailCheck(dto.getCEmail())) 
			throw new JobFailException("사용중인 이메일입니다");
		
		Consumer consumer = dto.toEntity();
		
		MultipartFile profile = dto.getCProfile();
		
		String profileName = "default.jpg";
		if(profile!=null && profile.isEmpty()==false) {
			File file = new File(profileFolder, profile.getOriginalFilename());
			try {
				profile.transferTo(file);
				profileName = profile.getOriginalFilename();
			} catch(IllegalStateException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		String checkcode = RandomStringUtils.randomAlphanumeric(20);
		String encodedPassword = passwordEncoder.encode(consumer.getCPassword());
		consumer.addJoinInfo(profileName, checkcode, encodedPassword, Levels.BRONZE);
		dao.cMemberJoin(consumer);
		mailUtil.sendCheckMail("hompajo27@gmail.com", consumer.getCEmail(), checkcode);
	}
	
	public void cFindId(ConsumerDto.InputFindId dto) {
		// Consumer consumer = 
		ConsumerDto.OutputFindId consumer = dao.cFindId(dto.getCEmail()).orElseThrow(()->new CounsumerNotFoundException());
		mailUtil.sendFindIdMail("hompajo27@gmail.com", dto.getCEmail(), consumer.getCId());
	}
}
