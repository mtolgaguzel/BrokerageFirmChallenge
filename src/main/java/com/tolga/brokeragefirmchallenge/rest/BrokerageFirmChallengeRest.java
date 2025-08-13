package com.tolga.brokeragefirmchallenge.rest;

import com.tolga.brokeragefirmchallenge.constants.Result;
import com.tolga.brokeragefirmchallenge.constants.Status;
import com.tolga.brokeragefirmchallenge.dto.CreateOrderDto;
import com.tolga.brokeragefirmchallenge.dto.ListOrderRequest;
import com.tolga.brokeragefirmchallenge.entity.Asset;
import com.tolga.brokeragefirmchallenge.entity.Order;
import com.tolga.brokeragefirmchallenge.service.AssetService;
import com.tolga.brokeragefirmchallenge.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BrokerageFirmChallengeRest implements Serializable {
	@Autowired
	private AssetService assetService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/createOrder")
	public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDto createOrderDto, HttpServletRequest request) {
		try {
			return assetService.save(createOrderDto, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping( "/listOrders")
	public List<Order>  listOrders(@RequestBody ListOrderRequest listOrderRequest)  {
		List<Order> listOrders = new ArrayList<>();
		try {
			listOrders = orderService.findByCustomerAndDateRange(listOrderRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return listOrders;
		}
	}
	
	
	@GetMapping( "/deleteOrder/{customerId}")
	public ResponseEntity<String> deleteOrder(@PathVariable("customerId") Long customerId) {
		String result = null;
		try {
			Order order = orderService.findById(customerId);
			
			if(order != null && order.getStatus().equals(Status.PENDING)) {
				orderService.deleteById(customerId);
				assetService.updateAsset(customerId, order);
				
				result = Result.SUCCESS.name();
			}
			else
				result = "The order is not in pending status or there is no order for this customer";
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return ResponseEntity.ok().body(result);
		}
	}
	
	@GetMapping( "/listAssets/{customerId}")
	public List<Asset> listAssets(@PathVariable Long customerId)  {
		List<Asset> assetList = new ArrayList<>();
		try {
			assetList = assetService.findAllByCustomerId(customerId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return assetList;
		}
	}
}
