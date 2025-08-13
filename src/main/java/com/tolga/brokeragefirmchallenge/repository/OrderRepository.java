package com.tolga.brokeragefirmchallenge.repository;

import com.tolga.brokeragefirmchallenge.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByCustomerIdAndCreateDateBetween(Long customerId, Date createDateAfter, Date createDateBefore);
}
