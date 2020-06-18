package com.casestudy.odcw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{

	List<Customer> findByStatus(String status);

	Customer findByEmail(String email);
}
