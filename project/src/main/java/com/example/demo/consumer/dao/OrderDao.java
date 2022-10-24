package com.example.demo.consumer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.OrderStatus;
import com.example.demo.consumer.dto.OrderDto;
import com.example.demo.consumer.dto.OrderDto.OrderReadDto;
import com.example.demo.consumer.entity.Aorder;

@Mapper
public interface OrderDao {
	
	// 주문하기 
	public Integer cOrder(Aorder aorder);
	
	// 주문 상태 확인
	public OrderStatus cOrderStatusCheck(Integer aOrderNum, String cId);
	
	// 주문 내역 출력 dto 값이 있을 경우 동적 where절 활성화
	public List<OrderDto.ListAllDto> cOrderListRead(OrderReadDto dto);
	
	// 주문 취소
	public Integer cOrderCancel(Integer aOrderNum, String cId);	
	
	// 총합 업데이트
	public Integer totalPriceUpdate(Integer aOrderNum, Integer aTotalPrice, String cId);
	
	// 주문 상세 내역 출력
	public List<OrderDto.ListAllDto> cOrderListReadAll(String cId);
	
	// 주문번호 찾기
	public Boolean findByOrderNum(Integer aOrderNum, String cId);
	 

}
