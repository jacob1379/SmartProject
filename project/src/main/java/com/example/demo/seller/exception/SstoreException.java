package com.example.demo.seller.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SstoreException extends RuntimeException{

	String message;
	@Override
	public String getMessage() {
		return message;
	}
}
