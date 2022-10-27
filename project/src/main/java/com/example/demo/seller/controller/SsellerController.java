package com.example.demo.seller.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping(path = "/seller/check/sId", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sIdDoubleCheck(String sId) {
		service.sIdAvailable(sId);
		return ResponseEntity.ok(new sRestResponse("OK", "사용할 수 있는 아이디입니다", null));
	}

	// 이메일 중복 확인
	@GetMapping(value = "/seller/check/sEmail", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sEmailDoubleCheck(String sEmail) {
		service.sEmailAvailable(sEmail);
		return ResponseEntity.ok(new sRestResponse("OK", "사용할 수 있는 이메일입니다", null));
	}

	// 사업자번호 중복 확인
	@GetMapping(value = "/seller/check/sBusiness", produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<sRestResponse> sbNumDoubleCheck(String sBusinessNum) { 
		service.sBNumOverlap(sBusinessNum); 
		return ResponseEntity.ok(new sRestResponse("OK", "사용할 수 있는 사업자번호 입니다", null)); }
	
	// 회원가입
	@PostMapping(value = "/seller/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sellerJoin(SsellerDto.Join dto) throws MessagingException {
		System.out.println("00000000000000000000000000000000000");
		service.join(dto);
		System.out.println("11111111111111111111111111111111111");
		return ResponseEntity.ok(new sRestResponse("OK", "가입 성공", "/login"));

	}

	
	 // 내정보 보기 
	 //@PreAuthorize("isAuthenticated()")
	  @GetMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<sRestResponse> sellerRead(Principal principal) {
		  SsellerDto.Read dto = service.read(principal.getName()); 
		  return ResponseEntity.ok(new sRestResponse("OK", dto, null));   
	}
	  
	  // 확장로 데이터의 MIME 타입을 리턴하는 함수
	  // jpg, png, gif > 확장자가 틀리면 브라우저가 제대로 처리 x
	  private MediaType getMediaType(String imagename) {
			// spring11.jpg -> .을 찾아서 .다음부터 자르면 확장자
			int position = imagename.lastIndexOf(".");
			String ext = imagename.substring(position+1).toUpperCase();
			if(ext.equals("JPG"))
				return MediaType.IMAGE_JPEG;
			else if(ext.equals("PNG"))
				return MediaType.IMAGE_PNG;
			else 
				return MediaType.IMAGE_GIF;
		}
	
	// 프사 보기
		@GetMapping(path="/profile/{imagename}", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
		public ResponseEntity<byte[]> showProfile(@PathVariable String imagename) {
			File file = new File("c:/upload/profile", imagename);
			if(file.exists()==false)
				return null;
			// 요청, 응답은 헤더와 바디로 구성
			// 헤더는 바디의 종류, 취급 방법등의 메타 데이터가 들어간다
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(getMediaType(imagename));
			
			// inline을 주면 브라우저 처리, attachment를 주면 다운로드
			headers.add("Content-Disposition", "inline;filename="+imagename);
			try {
				// 파일은 byte[]로 내보내면 된다
				// 파일을 byte의 배열로 바꾸는 방법들이 몇가지가 있는데 그 중
				// 한 줄짜리
				return ResponseEntity.ok().headers(headers)
						.body(Files.readAllBytes(file.toPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	// 이메일로 아이디 찾기
	@GetMapping(path = "/seller/find/sId", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sellerFindId(@Valid SsellerDto.findIdByEmail dto , BindingResult bindingResult)
			throws MessagingException {
		service.sFindIdByEmail(dto);
		return ResponseEntity.ok(new sRestResponse("OK", "아이디를 이메일로 보냈습니다", null));
	}

	// 아이디와 이메일을 입력받아 임시비밀번호 보내기
	@PatchMapping(path = "/seller/find/password", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sellerTemporaryPassword(@Valid SsellerDto.stPassword dto, BindingResult bindingResult)
			throws MessagingException {
		service.sTemporaryPassword(dto);
		return ResponseEntity.ok(new sRestResponse("OK", "임시비밀번호를 이메일로 보냈습니다", null));
	}

	// 비밀번호 변경 
	@PreAuthorize("isAuthenticated()")
	@PatchMapping(path = "/seller/scpassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sellerChangePassword(@Valid SsellerDto.scPassword dto, BindingResult bindingResult,
			Principal principal) {
		service.sChangePassword(dto, principal.getName());
		return ResponseEntity.ok(new sRestResponse("OK", "", null));
	}

	// 내정보 변경
	@PreAuthorize("isAuthenticated()")
	@PostMapping(path = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> sellerUpdate(@Valid SsellerDto.Update dto, BindingResult bindingResult,
			Principal principal) {
		service.update(dto, principal.getName());
		return ResponseEntity.ok(new sRestResponse("OK", "정보를 변경했습니다", "/seller/read"));
	}
	

	// 회원 탈퇴 
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(path = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<sRestResponse> resign(SecurityContextLogoutHandler handler, HttpServletRequest req,
			HttpServletResponse res, Authentication authentication) {
		service.sellerOut(authentication.getName());
		handler.logout(req, res, authentication);
		return ResponseEntity.ok(new sRestResponse("OK", "회원 정보를 삭제했습니다", "/seller/list"));
	}
}