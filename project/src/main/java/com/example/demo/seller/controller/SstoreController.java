package com.example.demo.seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.seller.service.SstoreService;

@Controller
public class SstoreController {

	@Autowired
	private SstoreService service;
}
