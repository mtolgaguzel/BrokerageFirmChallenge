package com.tolga.brokeragefirmchallenge.service;

import com.tolga.brokeragefirmchallenge.dto.ListOrderRequest;
import com.tolga.brokeragefirmchallenge.entity.Order;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public interface OrderService extends Serializable {
	Order findById(Long id);
	List<Order> findAll();
	Order save(Order Order);
	void deleteById(Long id);
	Order update(Order toBeUpd, Long id);
	
	List<Order> findByCustomerAndDateRange(ListOrderRequest listOrderRequest);
}
