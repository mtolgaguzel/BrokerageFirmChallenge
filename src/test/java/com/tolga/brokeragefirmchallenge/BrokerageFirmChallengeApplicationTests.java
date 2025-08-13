package com.tolga.brokeragefirmchallenge;

import com.tolga.brokeragefirmchallenge.constants.Result;
import com.tolga.brokeragefirmchallenge.constants.SideEnum;
import com.tolga.brokeragefirmchallenge.constants.Status;
import com.tolga.brokeragefirmchallenge.dto.CreateOrderDto;
import com.tolga.brokeragefirmchallenge.dto.ListOrderRequest;
import com.tolga.brokeragefirmchallenge.entity.Asset;
import com.tolga.brokeragefirmchallenge.entity.Order;
import com.tolga.brokeragefirmchallenge.repository.AssetRepository;
import com.tolga.brokeragefirmchallenge.repository.OrderRepository;
import com.tolga.brokeragefirmchallenge.service.AssetService;
import com.tolga.brokeragefirmchallenge.service.OrderService;
import com.tolga.brokeragefirmchallenge.utils.Constants;
import org.apache.ibatis.io.ResolverUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BrokerageFirmChallengeApplicationTests {
	
	private final Asset asset = Asset.builder().assetName("TRY").size(BigDecimal.valueOf(50)).usableSize(BigDecimal.valueOf(1000)).customerId(1L).build();
	private final Order order = Order.builder().customerId(1L).assetName("TRY").orderSide(SideEnum.BUY).price(BigDecimal.valueOf(1)).size(BigDecimal.valueOf(1.5D)).status(Status.PENDING).build();
	private final CreateOrderDto dto = CreateOrderDto.builder().customerId(1L).assetName("TRY").orderSide(SideEnum.BUY).status(Status.PENDING).price(BigDecimal.valueOf(1.5D)).size(BigDecimal.valueOf(1)).build();
	
	
	@Mock
	private AssetService assetService;
	
	@Mock
	private OrderService orderService;
	
	@Mock
	private AssetRepository  assetRepository;
	
	@Mock
	private OrderRepository orderRepository;
	
	
	@Test
	public void createOrder() {
		try {
			Mockito.when(assetRepository.findByCustomerIdAndAssetName(dto.getCustomerId(), Constants.TRY)).thenReturn(asset);
			
			Mockito.doReturn(orderService);
			
			Mockito.when(orderRepository.save(order)).thenReturn(any());
			
			MockHttpServletRequest request = new MockHttpServletRequest();
			Mockito.when(assetService.save(dto, request)).thenReturn(ResponseEntity.ok(order));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void  listOrders() {
		try {
			Mockito.doReturn(orderService);
			
			Mockito.when(orderRepository.save(order)).thenReturn(new Order());
			ListOrderRequest listOrderRequest = new ListOrderRequest();
			
			listOrderRequest.setCustomerId(1L);
			ZoneId zoneId = ZoneId.systemDefault();
			LocalDateTime localDateTime = LocalDateTime.now();
			localDateTime.minusDays(2L);
			Instant instant = localDateTime.atZone(zoneId).toInstant();
			listOrderRequest.setDate1(new java.sql.Date(instant.toEpochMilli()));
			listOrderRequest.setDate2(new java.sql.Date(System.currentTimeMillis()));
			
			List<Order> listOrder = new ArrayList<Order>();
			listOrder.add(order);
			Mockito.when(orderRepository.findByCustomerIdAndCreateDateBetween(listOrderRequest.getCustomerId(), listOrderRequest.getDate1(), listOrderRequest.getDate2()))
					.thenReturn(any());
			
			Mockito.when(orderService.findByCustomerAndDateRange(listOrderRequest)).thenReturn(new ArrayList<Order>());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void  deleteOrder() {
		try {
			Mockito.doReturn(orderService);
			Mockito.doReturn(assetService);
			Mockito.doNothing().when(orderRepository).deleteById(isA(Long.class));
			
			
			Mockito.when(orderService.findById(1L)).thenReturn(order);
			
			if(order != null && order.getStatus().equals(Status.PENDING)) {
				orderService.deleteById(1L);
				assetService.updateAsset(1L, order);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void  listAssets() {
		try {
			Mockito.when(assetRepository.findAllByCustomerId(1L)).thenReturn(new ArrayList<>());
			Mockito.doReturn(assetService);
			
			List<Asset> assetList = assetService.findAllByCustomerId(1L);
			assertEquals(assetList.size(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
