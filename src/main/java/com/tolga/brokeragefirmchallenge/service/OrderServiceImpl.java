package com.tolga.brokeragefirmchallenge.service;

import com.tolga.brokeragefirmchallenge.dto.ListOrderRequest;
import com.tolga.brokeragefirmchallenge.entity.Order;
import com.tolga.brokeragefirmchallenge.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@Override
	public Order findById(Long id) {
		return orderRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Order> findAll() {
		return (List<Order>) orderRepository.findAll();
	}
	
	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}
	
	@Override
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}
	
	@Override
	public Order update(Order toBeUpd, Long id) {
		Order orderDB = orderRepository.findById(id).get();
		
		BeanUtils.copyProperties(toBeUpd, orderDB);
		
		return orderRepository.save(orderDB);
	}
	
	@Override
	public List<Order>  findByCustomerAndDateRange(ListOrderRequest listOrderRequest) {
		return orderRepository.findByCustomerIdAndCreateDateBetween(listOrderRequest.getCustomerId(), listOrderRequest.getDate1(), listOrderRequest.getDate2());
	}
}
