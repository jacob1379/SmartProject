package com.example.demo.seller.dto;


import lombok.*;

// 성공/실패시 응답 형식을 통일하기 위한 DTO
@Data
@AllArgsConstructor
public class RestResponse {
	private String message;
	private Object result;
	private String url;
}
