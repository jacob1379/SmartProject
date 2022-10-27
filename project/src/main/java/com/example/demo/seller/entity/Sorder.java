package com.example.demo.seller.entity;

import java.time.LocalDate;

import com.example.demo.OrderStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Sorder {
	private Integer aOrderNum;
	private LocalDate aOrderDate;
	private OrderStatus aOrderStatus;
	private String cId;
	private String aDeleveryAddress;
	private String aDetailAddress;
	private Integer sStoreNum;
	private Integer aTotalPrice;
	
	
}
