package com.example.demo.seller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.seller.dao.SorderDao;
import com.example.demo.seller.dto.OrderDto;
import com.example.demo.seller.entity.Sorder;

@Service
public class SorderService {

	@Autowired
	SorderDao dao;
	
	public List<OrderDto.OrderRead> orderRead(Integer sStoreNum) {
	System.out.println(dao.orderListRead(sStoreNum));
		return dao.orderListRead(sStoreNum);
	}
}
