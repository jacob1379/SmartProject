package com.example.demo.seller.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SellerJobFailException extends RuntimeException {
	String message;
	@Override
	public String getMessage() {
		return message;
	}
}
