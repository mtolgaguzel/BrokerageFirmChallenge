package com.tolga.brokeragefirmchallenge.service;

import com.tolga.brokeragefirmchallenge.dto.CreateOrderDto;
import com.tolga.brokeragefirmchallenge.entity.Asset;
import com.tolga.brokeragefirmchallenge.entity.Order;
import com.tolga.brokeragefirmchallenge.repository.AssetRepository;
import com.tolga.brokeragefirmchallenge.utils.Constants;
import com.tolga.brokeragefirmchallenge.utils.DtoConverter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService{
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public Asset findById(Long id) {
				return assetRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Asset> findAllByCustomerId(Long id) {
		return assetRepository.findAllByCustomerId(id);
	}
	
	@Override
	public List<Asset> findAll() {
		return (List<Asset>) assetRepository.findAll();
	}
	
	@Override
	public Asset save(Asset asset) {
		return assetRepository.save(asset);
	}
	
	@Override
	public ResponseEntity save(CreateOrderDto createOrderDto, HttpServletRequest request) {
		Boolean result = Boolean.FALSE;
		Order order = null;
		URI location = null;
		try {
			Asset tryAsset = findByCustomerIdAndAssetName(createOrderDto.getCustomerId(), Constants.TRY);
			
			if(tryAsset != null && tryAsset.getUsableSize().compareTo(createOrderDto.getSize().multiply(createOrderDto.getPrice()))==1){
				createOrderDto.setCustomerId(createOrderDto.getCustomerId());
				order = orderService.save(DtoConverter.orderConverter(createOrderDto));
				location = ServletUriComponentsBuilder.fromRequestUri(request)
						           .path("/{id}")
						           .buildAndExpand(order.getCustomerId())
						           .toUri();
				tryAsset.setUsableSize(tryAsset.getUsableSize().subtract(createOrderDto.getSize().multiply(createOrderDto.getPrice())));
				save(tryAsset);
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		finally {
			if(result)
				return ResponseEntity.created(location).body(order);
			else
				return ResponseEntity.badRequest().build();
		}
		
	}
	
	@Override
	public void updateAsset(Long customerId, Order order) {
		Asset tryAsset = findByCustomerIdAndAssetName(customerId, Constants.TRY);
		tryAsset.setUsableSize(tryAsset.getUsableSize().add(order.getSize().multiply(order.getPrice())));
		save(tryAsset);
	}
	
	@Override
	public void deleteById(Long id) {
		assetRepository.deleteById(id);
	}
	
	@Override
	public Asset update(Asset toBeUpd, Long id) {
		Asset assetDB = assetRepository.findById(id).get();
		
		BeanUtils.copyProperties(toBeUpd, assetDB);
		return assetRepository.save(assetDB);
	}
	
	@Override
	public Asset findByCustomerIdAndAssetName(Long customerId, String assetName) {
		return assetRepository.findByCustomerIdAndAssetName(customerId, assetName);
	}
}
