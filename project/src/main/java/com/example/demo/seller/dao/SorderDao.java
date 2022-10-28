package com.example.demo.seller.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.consumer.dto.OrderDto.OrderReadDto;
import com.example.demo.seller.dto.OrderDto;
import com.example.demo.seller.entity.Sorder;

@Mapper
public interface SorderDao {

	
 public List<OrderDto.OrderRead> orderListRead(Integer sStoreNum);
 
 
 public Integer statusUpdate(Sorder order);
}
