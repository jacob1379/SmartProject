package com.example.demo.seller.dto;

import java.time.LocalDate;

import com.example.demo.OrderStatus;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDto {
	
	@Data
	@Builder
	public static class OrderRead {
		private Integer aOrderNum;
		//private LocalDate aOrderDate;
		private OrderStatus aOrderStatus;
		private String cId;
		private String aDeleveryAddress;
		private String aDetailAddress;
		private Integer sStoreNum;
		private Integer aTotalPrice;
		
	}
	
	@Data
	@Builder
	public static class statusUpdate {
		private Integer aOrderNum;
		private OrderStatus aOrderStatus;
	}
}
