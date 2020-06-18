package com.casestudy.odcw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.CarDetails;

public interface CarDetailsRepository extends MongoRepository<CarDetails, String>{
	
	CarDetails findByNumber(String number);

	List<CarDetails> findByStatus(String status);

}
