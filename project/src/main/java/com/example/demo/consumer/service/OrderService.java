package com.example.demo.consumer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.OrderStatus;
import com.example.demo.consumer.dao.BasketDao;
import com.example.demo.consumer.dao.OrderDao;
import com.example.demo.consumer.dao.OrderDetailDao;
import com.example.demo.consumer.dto.OrderDetailDto;
import com.example.demo.consumer.dto.OrderDto;
import com.example.demo.consumer.dto.OrderDto.OrderReadDto;
import com.example.demo.consumer.entity.Aorder;
import com.example.demo.consumer.entity.AorderDetail;



@Service
public class OrderService {
	
	@Autowired
	OrderDao aorderDao;	
	
	@Autowired
	OrderDetailDao aorderDetailDao;
	
	@Autowired
	BasketDao basketDao;
	
	public Aorder cOrder(OrderDto.OrderSave orderdto) {
			
		Aorder aor = orderdto.toentity();
		aor.addinfo(OrderStatus.접수대기, 0);
		aorderDao.cOrder(aor);
		System.out.println(aor);
		for(int i=0; i<basketDao.cBasketListSize(orderdto.getCId());i++) {
			OrderDetailDto.SaveDto dto = OrderDetailDto.SaveDto.builder().aOrderNum(aor.getAOrderNum()).sMenuCode(aorderDetailDao.basketListGet(orderdto.getCId()).get(i).getSMenuCode()).sGroupNum(aorderDetailDao.basketListGet(orderdto.getCId()).get(i).getSGroupNum()).aCount(aorderDetailDao.basketListGet(orderdto.getCId()).get(i).getCMenuCount()).build();
			AorderDetail result = dto.toEntity();
			aorderDetailDao.cOrderDetailSave(result);
		}
		
		List<Integer> total = new ArrayList<>();
		int totalPirce = 0; 		
		List<OrderDetailDto.PriceDto> value = aorderDetailDao.menuPriceCountGet(aor.getAOrderNum(), aor.getCId());
		
		for(int i=0; i< aorderDetailDao.OrderDetailByOrder(aor.getAOrderNum()); i++) {
			int count = value.get(i).getACount();
			int price = value.get(i).getSMenuPrice();
			
			total.add(i, count * price);
		}
		
		for(int i=0; i<total.size(); i++) {
			totalPirce = totalPirce + total.get(i);
		}
		
		aor.addTotalPrice(totalPirce);
		
		aorderDao.totalPriceUpdate(aor.getAOrderNum(),aor.getATotalPrice(),aor.getCId());
		
		basketDao.cBasketListDelete(orderdto.getCId(), null);

		return aor;
	}

	public List<OrderDto.ListAllDto> OrderListAll(String cId) {	 
		
		return aorderDao.cOrderListReadAll(cId);
	}

	public List<OrderDto.ListAllDto> cOrderListRead(OrderReadDto dto) {
		return aorderDao.cOrderListRead(dto);
	}
	
	public String OrderCansle(Integer aOrderNum, String cId) {
		
		if(aorderDao.cOrderStatusCheck(aOrderNum, cId) == OrderStatus.접수대기) {

			aorderDao.cOrderCancel(aOrderNum, cId);
			
			aorderDetailDao.cOrderDetailDelete(aOrderNum, cId);
			
			return "주문이 취소되었습니다";
		}
		
		return "주문이 접수되어 취소 불가능합니다";

	}
	
	public Boolean findOrderNum(Integer aOrderNum, String cId) {
		if(aorderDao.findByOrderNum(aOrderNum, cId)) {
			return true;
		}
		return false;
	}
}
