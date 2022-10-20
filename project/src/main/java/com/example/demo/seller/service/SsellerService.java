package com.example.demo.seller.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.seller.dao.SsellerDao;
import com.example.demo.seller.dto.SsellerDto;
import com.example.demo.seller.dto.SsellerDto.Read;
import com.example.demo.seller.entity.Sseller;
import com.example.demo.seller.entity.sLevel;
import com.example.demo.seller.exception.SellerJobFailException;
import com.example.demo.seller.exception.SellerNotFoundException;
import com.example.demo.seller.util.SsellerMailUtil;

@Service
public class SsellerService {

	@Autowired
	private SsellerDao sellerdao;
	@Autowired
	private SsellerMailUtil mailUtil;
	@Autowired
	private PasswordEncoder passwordEncoder; // 시큐리티 할때 쓰는듯?
	
	@PostConstruct
	public void que() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!");
	}

	// 회원가입
	public void join(SsellerDto.Join dto) throws MessagingException {
		if(sellerdao.SellerExistsById(dto.getSId()))
			throw new SellerJobFailException("사용중인 아이디 입니다");
		if(sellerdao.SellerExistsByEmail(dto.getSEmail()))
			throw new SellerJobFailException("사용중인 이메일 입니다");
		
		Sseller seller = dto.toEntity();
		
		// 체크코드 생성, 비밀번호 암호화
		String Checkcode = RandomStringUtils.randomAlphanumeric(20);
		String encodedPassword = passwordEncoder.encode(seller.getSPassword());
		seller.sAddJoinInfo(Checkcode, encodedPassword, sLevel.BRONZE);
		sellerdao.SellerSave(seller);
		mailUtil.sSendCheckMail("projectseller1111@gmail.com", seller.getSEmail(), Checkcode);
	}
	
	// 이메일로 아이디 찾기
	public void sFindIdByEmail(SsellerDto.findIdByEmail dto) throws MessagingException {
		Sseller seller = sellerdao.sFindByEmail(dto.getSEmail()).orElseThrow(()->new SellerNotFoundException());
		mailUtil.sSendFindIdMail("projectseller1111@gmail.com", dto.getSEmail(), seller.getSId());
	}

	// 비밀번호 초기화
	public void sTemporaryPassword(SsellerDto.stPassword dto) throws MessagingException {
		Sseller seller = sellerdao.stPassword(dto.getSId()).orElseThrow(()->new SellerNotFoundException());
		if(seller.getSEmail().equals(dto.getSEmail())==false)
			throw new SellerNotFoundException();
		String newPassword = RandomStringUtils.randomAlphanumeric(20);
		sellerdao.SellerUpdate(Sseller.builder().sId(dto.getSId()).sPassword(passwordEncoder.encode(newPassword)).build());
		mailUtil.sSendResetPasswordMail("projectseller1111@gmail.com", dto.getSEmail(), newPassword);
	}
	
	// 비밀번호 변경
	public void sChangePassword(SsellerDto.scPassword dto, String loginId) {
		Sseller seller = sellerdao.scPassword(loginId).orElseThrow(()->new SellerNotFoundException());
		String encodedPassword = seller.getSPassword();
		if(passwordEncoder.matches(dto.getSPassword(), encodedPassword)==false)
			throw new SellerJobFailException("비밀번호를 변경하지 못했습니다");
		sellerdao.SellerUpdate(Sseller.builder().sId(loginId).sPassword(passwordEncoder.encode(dto.getNewPssword())).build());
	}
	
	public Read read(String loginId) {
		Sseller seller = sellerdao.sFindId(loginId).orElseThrow(SellerNotFoundException::new);
		SsellerDto.Read dto = seller.toDto();
		Long days = ChronoUnit.DAYS.between(dto.getSJoinday(), LocalDate.now());
		dto.setDays(days);
		return dto;
	}
	
	public void checkJoin(String sCheckcode) {
		Sseller seller = sellerdao.sFindCheckcode(sCheckcode).orElseThrow(()->new SellerJobFailException("체크코드를 확인 할 수 없습니다"));
		sellerdao.SellerUpdate(Sseller.builder().sId(seller.getSId()).sEnabled(true).sCheckcode("").build());
	}
	
	// 스프링 스케쥴러
	@Scheduled(cron="0 0 4 ? * THU")
	public void notCheckJoinDelete() {
		List<String> sId = sellerdao.sFindCheckcodeIsNotEmpty();
		sellerdao.sNotCheckId(sId);
	}
	
	/*
	// 정보 변경
	public Integer update(SsellerDto.Update dto, String loginId) {
		String newEmail = dto.getSEmail();
		if(newEmail==null)
			throw new SellerJobFailException("변경할 값이 없습니다");
		Sseller seller = sellerdao.sFindId(loginId).orElseThrow(SellerNotFoundException::new);
		return sellerdao.SellerUpdate(Sseller.builder().sId(loginId).sEmail(newEmail).build());
	}
	*/
	// 회원 탈퇴
	public Integer sellerOut(String loginId) {
		if(sellerdao.SellerExistsById(loginId)==false)
			throw new SellerNotFoundException();
		return sellerdao.sDeleteId(loginId);
	}
	
/*	
	public Optional<SsellerDto.Read> Readimport(String sId) {
		return sellerdao.SellerRead(sId);
	}
*/
	// 판매자 회원 정보 수정 (임시)
	public Integer update(SsellerDto.Update dto) {
		return sellerdao.SellerUpdate(dto.toEntity());
	}

	/*
	public Integer delete(String sId) {
		return sellerdao.SellerDelete(sId);
	}
*/
	// 판매자 사업자번호 충복 확인
	public void sBNumOverlap(String sBusinessNum) {
		if (sellerdao.SellerBusinessNumOverlap(sBusinessNum))
			throw new SellerJobFailException("사용중인 사업자번호 입니다");
	}

	// 아이디 사용여부 확인
	public void sIdAvailable(String sId) {
		if (sellerdao.SellerExistsById(sId))
			throw new SellerJobFailException("사용중인 아이디입니다");
	}

	// 이메일 사용여부 확인
	public void sEmailAvailable(String sEmail) {
		if (sellerdao.SellerExistsByEmail(sEmail))
			throw new SellerJobFailException("사용중인 이메일입니다");
	}
}