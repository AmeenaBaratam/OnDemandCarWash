package com.casestudy.odcw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.CarDetails;

public interface CarDetailsRepository extends MongoRepository<CarDetails, String>{
	
	CarDetails findByNumber(String number);

}
