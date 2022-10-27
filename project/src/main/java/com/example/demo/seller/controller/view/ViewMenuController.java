package com.example.demo.seller.controller.view;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class ViewMenuController {
	
	@GetMapping(value = "menugroup/grouplist")
	public void grouplist() {
	}
}
