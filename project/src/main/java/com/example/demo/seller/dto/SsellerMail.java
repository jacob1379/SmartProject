package com.example.demo.seller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Builder
@Accessors(chain=true)
public class SsellerMail {
	private String from;	// 보내는 사람 이메일
	private String to;		// 받는 사람 이메일
	private String subject;	// 제목
	private String text;	// 내용
}
