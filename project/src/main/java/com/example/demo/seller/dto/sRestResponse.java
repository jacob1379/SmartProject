package com.example.demo.seller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class sRestResponse {
	private String message;
	private Object result;
	private String url;
}
