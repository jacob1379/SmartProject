package com.example.demo.seller.exception;

import lombok.*;

@AllArgsConstructor
public class JobFailException extends RuntimeException {
	String message;
}
