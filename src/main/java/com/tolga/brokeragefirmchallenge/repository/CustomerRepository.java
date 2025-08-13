package com.tolga.brokeragefirmchallenge.repository;

import com.tolga.brokeragefirmchallenge.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	Customer findByUserName(String userName);
}
