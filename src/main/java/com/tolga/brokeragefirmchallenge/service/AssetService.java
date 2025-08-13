package com.tolga.brokeragefirmchallenge.service;

import com.tolga.brokeragefirmchallenge.dto.CreateOrderDto;
import com.tolga.brokeragefirmchallenge.entity.Asset;
import com.tolga.brokeragefirmchallenge.entity.Order;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public interface AssetService extends Serializable {
	Asset findById(Long id);
	List<Asset> findAll();
	Asset save(Asset asset);
	void deleteById(Long id);
	Asset update(Asset toBeUpd, Long id);
	List<Asset> findAllByCustomerId(Long id);
	Asset findByCustomerIdAndAssetName(Long customerId, String assetName);
	ResponseEntity save(CreateOrderDto createOrderDto, HttpServletRequest request);
	void updateAsset(Long customerId, Order order);
}
