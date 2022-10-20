package com.example.demo.consumer.service;

import java.io.File;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.consumer.dao.ConsumerDao;
import com.example.demo.consumer.dto.ConsumerDto;
import com.example.demo.consumer.dto.ConsumerDto.Read;
import com.example.demo.consumer.entity.Consumer;
import com.example.demo.consumer.entity.Levels;
import com.example.demo.consumer.exception.ConsumerNotFoundException;
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
	
	// 서블릿의 생명주기 : init() -> service() -> destroy()
	// 스프링의 생명주기 어노테이션 : @PostConstruct, @PreDestroy
	
	@PostConstruct
	public void cTest() {
		System.out.println("!!!!!!!!!!");
	}
	// 아이디 중복확인
	public void idCheck(String cId) {
		if(dao.cIdCheck(cId))
			throw new JobFailException("사용중인 아이디입니다");
	}
	// 닉네임 중복확인
	public void nicknameCheck(String cNickname) {
		if(dao.cNicknameCheck(cNickname))
			throw new JobFailException("사용중인 닉네임입니다");
	}
	// 이메일 중복확인
	public void emailCheck(String cEmail) {
		if(dao.cEmailCheck(cEmail))
			throw new JobFailException("사용중인 이메일입니다");
	}
	// 회원가입
	public void join(ConsumerDto.Join dto) {
		if(dao.cIdCheck(dto.getCId())) 
			throw new JobFailException("사용중인 아이디입니다");
		if(dao.cNicknameCheck(dto.getCNickname())) 
			throw new JobFailException("사용중인 닉네임입니다");
		if(dao.cEmailCheck(dto.getCEmail())) 
			throw new JobFailException("사용중인 이메일입니다");
		
		Consumer consumer = dto.toEntity();
		
		MultipartFile profile = dto.getCProfile();
		// 프로필 사진이 있으면 저장하고 변경
		String profileName = "default.jpg";
		if(profile!=null && profile.isEmpty()==false) {
			// 폴더명, 파일명으로 빈 파일을 생성한다.
			File file = new File(profileFolder, profile.getOriginalFilename());
			try {
				profile.transferTo(file); //transferTo : MultipartFile 메소드, 파일 저장 메소드
				profileName = profile.getOriginalFilename();
			} catch(IllegalStateException e) { //메소드가 요구된 처리를 하기에 적합한 상태에 있지 않을때
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace(); //에러의 발생근원지를 찾아서 단계별로 에러를 출력합니다.
			}
		}
		// 체크코드 생성, 비밀번호 암호화
		String checkcode = RandomStringUtils.randomAlphanumeric(20);
		String encodedPassword = passwordEncoder.encode(consumer.getCPassword());
		consumer.addJoinInfo(profileName, checkcode, encodedPassword, Levels.BRONZE);
		dao.cMemberJoin(consumer);
		mailUtil.sendCheckMail("hompajo27@gmail.com", consumer.getCEmail(), checkcode);
	}
	// 아이디 찾기
	public void cFindId(ConsumerDto.FindId dto) {
		ConsumerDto.OutputFind consumer = dao.cFindId(dto.getCEmail()).orElseThrow(()->new ConsumerNotFoundException());
		mailUtil.sendFindIdMail("hompajo27@gmail.com", dto.getCEmail(), consumer.getCId());
	}
	// 비밀번호 찾기
	// 아이디로 검색 -> 없으면 ConsumerNotFoundException
	// 이메일 확인 -> 틀리면 ConsumerNotFoundException
	// 20글자 임시 비밀번호 생성 -> 암호화 -> 비번 변경 -> 메일 발송
	public void cFindPassword(ConsumerDto.FindPassword dto) {
		ConsumerDto.OutputFind consumer = dao.cFindPassword(dto.getCId()).orElseThrow(()->new ConsumerNotFoundException());
		if(consumer.getCEmail().equals(dto.getCEmail())==false)
			throw new ConsumerNotFoundException();
		String newPassword = RandomStringUtils.randomAlphanumeric(20);
		dao.cMemberUpdate(Consumer.builder().cId(dto.getCId()).cPassword(passwordEncoder.encode(newPassword)).build());
		mailUtil.sendFindPasswordMail("hompajo27@gmail.com", dto.getCEmail(), newPassword);
	}
	// 회원정보 확인
	public Read read(String cId) {
		Consumer consumer = dao.cMemberRead(cId).orElseThrow(ConsumerNotFoundException::new);
		ConsumerDto.Read dto = consumer.toRead();
		Long days = ChronoUnit.DAYS.between(dto.getCJoinday(), LocalDate.now()); // 두 개의 특정 날짜 사이의 차이를 일수(days)로 구하기
		dto.setCDays(days);
		dto.setCProfile(profilePath + dto.getCProfile());
		return dto;
	}
	// 비밀번호, 닉네임, 전화번호, 이메일, 프로필 변경
	public Integer update(ConsumerDto.Update dto, String loginId) {
		String newCNickname = dto.getCNickname();
		String newCPhone = dto.getCPhone();
		String newCEmail = dto.getCEmail();
		Consumer consumer = dao.cMemberRead(loginId).orElseThrow(ConsumerNotFoundException::new);
		if(newCNickname==null && newCPhone==null && newCEmail==null) {
			newCNickname = consumer.getCNickname();
			newCPhone = consumer.getCPhone();
			newCEmail = consumer.getCEmail();
		} else if(newCNickname==null && newCPhone!=null && newCEmail!=null) {
			newCNickname = consumer.getCNickname();
			newCPhone = dto.getCPhone();
			newCEmail = dto.getCEmail();
		} else if(newCNickname!=null && newCPhone==null && newCEmail!=null) {
			newCNickname = dto.getCNickname();
			newCPhone = consumer.getCPhone();
			newCEmail = dto.getCEmail();
		} else if(newCNickname!=null && newCPhone!=null && newCEmail==null) {
			newCNickname = dto.getCNickname();
			newCPhone = dto.getCPhone();
			newCEmail = consumer.getCEmail();
		} else if(newCNickname==null && newCPhone==null && newCEmail!=null) {
			newCNickname = consumer.getCNickname();
			newCPhone = consumer.getCPhone();
			newCEmail = dto.getCEmail();
		} else if(newCNickname!=null && newCPhone==null && newCEmail==null) {
			newCNickname = dto.getCNickname();
			newCPhone = consumer.getCPhone();
			newCEmail = consumer.getCEmail();
		} else if(newCNickname==null && newCPhone!=null && newCEmail==null) {
			newCNickname = consumer.getCNickname();
			newCPhone = dto.getCPhone();
			newCEmail = consumer.getCEmail();
		} else {
			newCNickname = dto.getCNickname();
			newCPhone = dto.getCPhone();
			newCEmail = dto.getCEmail();
		}
		
		MultipartFile cProfile = dto.getCProfile();
		String newCProfileName = "default.jpg";
		if(cProfile==null || cProfile.isEmpty()==true)
			throw new JobFailException("변경할 값이 없습니다");
		
		if(cProfile!=null && cProfile.isEmpty()==false) {
			File oldProfile = new File(profilePath, consumer.getCProfile());
			// 새 프사는 일단 기본 프사인 default.jpg
			if(oldProfile.exists())
				oldProfile.delete();
			File newCProfile = new File(profileFolder, cProfile.getOriginalFilename());
			try {
				cProfile.transferTo(newCProfile);
				// 프사 저장에 성공하면 새 프사 이름을 변경
				newCProfileName = cProfile.getOriginalFilename();
			} catch(IllegalStateException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return dao.cMemberUpdate(Consumer.builder().cId(loginId).cNickname(newCNickname)
				.cPhone(newCPhone).cEmail(newCEmail).cProfile(newCProfileName).build());
	}
	
}
