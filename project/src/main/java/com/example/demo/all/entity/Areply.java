package com.example.demo.all.entity;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
@ToString
public class Areply {

	private Integer aReplyNum;
	private String aReplyContent;
	private Date aReplyDate;
	private Integer aReviewNum;
	
}
