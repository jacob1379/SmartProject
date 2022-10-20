package com.example.demo.seller.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
// import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.seller.controller.editor.MyDatePropertyEditor;
import com.example.demo.seller.dto.SsellerDto;
import com.example.demo.seller.dto.sRestResponse;
import com.example.demo.seller.entity.Sseller;
import com.example.demo.seller.service.SsellerService;

@Validated
@Controller
public class SsellerController {
	@Autowired
	private SsellerService service;

	@InitBinder
	public void init(WebDataBinder wdb) {
		wdb.registerCustomEditor(LocalDate.class, new MyDatePropertyEditor());
	}

	// 아이디 중복 확인
	@GetMapping(path = "/seller/check/id", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sIdDoubleCheck(String sId) {
		service.sIdAvailable(sId);
		return ResponseEntity.ok(new sRestResponse("OK", "사용할 수 있는 아이디입니다", null));
	}

	// 이메일 중복 확인
	@GetMapping(value = "/seller/check/email", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sEmailDoubleCheck(String sEmail) {
		service.sEmailAvailable(sEmail);
		return ResponseEntity.ok(new sRestResponse("OK", "사용할 수 있는 이메일입니다", null));
	}

	// 사업자번호 중복 확인
	@GetMapping(value = "/seller/check/business", produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<sRestResponse> sbNumDoubleCheck(String sBusinessNum) { 
		service.sBNumOverlap(sBusinessNum); 
		return ResponseEntity.ok(new sRestResponse("OK", "사용할 수 있는 사업자번호 입니다", null)); }
	
	// 회원가입
	@PostMapping(value = "/seller/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sellerJoin(SsellerDto.Join dto) throws MessagingException {
		service.join(dto);
		return ResponseEntity.ok(new sRestResponse("OK", "가입 성공", "/login"));

	}

	
	 // 내정보 보기 - 보류
	 //@PreAuthorize("isAuthenticated()")
	  @GetMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<sRestResponse> sellerRead(Principal principal) {
		  SsellerDto.Read dto = service.read(principal.getName()); 
		  return ResponseEntity.ok(new sRestResponse("OK", dto, null));   
	}
	
	
	// 이메일로 아이디 찾기
	@GetMapping(path = "/seller/find/id", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sellerFindId(@Valid SsellerDto.findIdByEmail dto , BindingResult bindingResult)
			throws MessagingException {
		service.sFindIdByEmail(dto);
		return ResponseEntity.ok(new sRestResponse("OK", "아이디를 이메일로 보냈습니다", null));
	}

	// 아이디와 이메일을 입력받아 임시비밀번호 보내기
	@PatchMapping(path = "/seller/stpassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sellerTemporaryPassword(@Valid SsellerDto.stPassword dto, BindingResult bindingResult)
			throws MessagingException {
		service.sTemporaryPassword(dto);
		return ResponseEntity.ok(new sRestResponse("OK", "임시비밀번호를 이메일로 보냈습니다", null));
	}

	// 비밀번호 변경 - 보류
	//@PreAuthorize("isAuthenticated()")
	@PatchMapping(path = "/seller/scpassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sellerChangePassword(@Valid SsellerDto.scPassword dto, BindingResult bindingResult,
			Principal principal) {
		service.sChangePassword(dto, principal.getName());
		return ResponseEntity.ok(new sRestResponse("OK", "", null));
	}
/*
	// 내정보 변경 - 보류
	//@PreAuthorize("isAuthenticated()")
	@PostMapping(path = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sellerUpdate(@Valid SsellerDto.Update dto, BindingResult bindingResult,
			Principal principal) {
		service.update(dto, principal.getName());
		return ResponseEntity.ok(new sRestResponse("OK", "정보를 변경했습니다", "/seller/read"));
	}
	*/
/*
	// 회원 탈퇴 - 보류
	//@PreAuthorize("isAuthenticated()")
	@DeleteMapping(path = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> resign(SecurityContextLogoutHandler handler, HttpServletRequest req,
			HttpServletResponse res, Authentication authentication) {
		service.sellerOut(authentication.getName());
		handler.logout(req, res, authentication);
		return ResponseEntity.ok(new RestResponse("OK", "회원 정보를 삭제했습니다", "/seller/list"));
	}
*/
	
	/*
	  // 판매자 회원 등록
	  @PostMapping(value = "/seller/new", produces =
	  MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Sseller>
	  join(@ModelAttribute SsellerDto.Join dto) { return
	  ResponseEntity.ok(service.join(dto)); }
	  */
	
	
	  // 판매자 정보 수정 - 임시
	  @PutMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Integer> update(@ModelAttribute SsellerDto.Update dto){ 
		  return ResponseEntity.ok(service.update(dto)); 
	  }
	  
	
	/*
	  // 판매자 회원 탈퇴
	  @DeleteMapping(value = "/seller", produces =
	  MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Integer>
	  delete(@RequestParam String sId) { return
	  ResponseEntity.ok(service.delete(sId)); }
	  */
	
	/*
	  // 판매자 회원 조회
	  @GetMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Optional<SsellerDto.Read>> read(@RequestParam String
	  sId) { return ResponseEntity.ok(service.Read(sId)); }
*/
	  
}