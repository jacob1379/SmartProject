package com.example.demo.consumer.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViewController {
	
	@GetMapping("/basket/cbasket")
	public void list() {
		
	}
	
	@GetMapping("/order/corder")
	public void order() {
		
	}
	
	@GetMapping("/order/ordersuccess")
	public void orderSuccess() {
		
	}
	
	@GetMapping("/main/home")
	public void main() {
		
	}
	
	@GetMapping("/main/storelist")
	public void storelist() {
		
	}
	
	@GetMapping("/main/storeview")
	public void storeview() {
		
	}
	
	@GetMapping("/main/login")
	public void login() {
		
	}
	

}
