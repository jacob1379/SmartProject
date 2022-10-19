package com.example.demo.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsumerResponseDto {
	private String message;
	private Object result;
	private String url;
}
