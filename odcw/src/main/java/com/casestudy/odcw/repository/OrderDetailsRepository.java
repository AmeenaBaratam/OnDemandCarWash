package com.casestudy.odcw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.OrderDetails;

public interface OrderDetailsRepository extends MongoRepository<OrderDetails, String>{

	List<OrderDetails> findByOrderStatus(String status);

}
