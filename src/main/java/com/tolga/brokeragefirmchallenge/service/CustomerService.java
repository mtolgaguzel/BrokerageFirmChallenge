package com.tolga.brokeragefirmchallenge.service;

import com.tolga.brokeragefirmchallenge.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerService {
	Customer findById(Long id);
	List<Customer> findAll();
	Customer save(Customer customer);
	void deleteById(Long id);
	Customer update(Customer toBeUpd, Long id);
	Customer findByUserName(String username);
}
