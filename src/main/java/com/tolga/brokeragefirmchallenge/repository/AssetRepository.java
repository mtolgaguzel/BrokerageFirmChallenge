package com.tolga.brokeragefirmchallenge.repository;

import com.tolga.brokeragefirmchallenge.entity.Asset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends CrudRepository<Asset, Long> {
	List<Asset> findAllByCustomerId(Long customerId);
	
	Asset findByCustomerIdAndAssetName(Long customerId, String assetName);
}
