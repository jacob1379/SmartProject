package com.example.demo.seller.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.seller.dto.SsellerDto;
import com.example.demo.seller.service.SsellerService;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class SsellerViewController {
	@Autowired
	private SsellerService service;

	@PreAuthorize("isAnonymous()")
	@GetMapping("/seller/sjoin")
	public void join() {
	}
	
	@GetMapping("/seller/login")
	public void login() {
		
	}
	
	@PreAuthorize("isAnonymous()")
	@GetMapping("/seller/check/join")
	public String checkJoin(String sCheckcode) {
		service.checkJoin(sCheckcode);
		return "/seller/login";
	}
	
	@PreAuthorize("isAnonymous()")
	@GetMapping("/seller/find")
	public void find() {
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/seller/read")
	public void sellerRead() {
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/seller/change_password")
	public ModelAndView changePassword(HttpSession session) {
		ModelAndView mav = new ModelAndView("/seller/change_password");
		if(session.getAttribute("msg")!=null) {
			mav.addObject("msg", session.getAttribute("msg"));
			session.removeAttribute("msg");
		}
		return mav;
	}
}	
	

