package com.tolga.brokeragefirmchallenge.utils;

import com.tolga.brokeragefirmchallenge.constants.Status;
import com.tolga.brokeragefirmchallenge.dto.CreateOrderDto;
import com.tolga.brokeragefirmchallenge.entity.Order;

import java.io.Serializable;
import java.time.LocalDate;

public class DtoConverter implements Serializable {
	
	public static Order orderConverter (CreateOrderDto createOrderDto){
		Order order = new Order();
		order.setCustomerId(createOrderDto.getCustomerId());
		order.setAssetName(createOrderDto.getAssetName());
		order.setOrderSide(createOrderDto.getOrderSide());
		order.setSize(createOrderDto.getSize());
		order.setPrice(createOrderDto.getPrice());
		order.setCreateDate(java.sql.Date.valueOf(LocalDate.now()));
		order.setStatus(Status.PENDING);
		return order;
	}
}
