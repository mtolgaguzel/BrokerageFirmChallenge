package com.tolga.brokeragefirmchallenge.service;

import com.tolga.brokeragefirmchallenge.entity.Customer;
import com.tolga.brokeragefirmchallenge.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer findById(Long id) {
		return customerRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Customer> findAll() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
	@Override
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}
	
	@Override
	public Customer update(Customer toBeUpd, Long id) {
		Customer custDB = customerRepository.findById(id).get();
		
		BeanUtils.copyProperties(toBeUpd, custDB);
		return custDB;
	}
	
	@Override
	public Customer findByUserName(String username){
		return customerRepository.findByUserName(username);
	}
}
