package com.casestudy.odcw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.odcw.model.CarDetails;

@Repository
public interface CarDetailsRepository extends MongoRepository<CarDetails, String>{
	
	Optional<CarDetails> findById(String id);
	List<CarDetails> findByStatus(String status);
	CarDetails findByBrandAndCategoryAndName(String brand,String category,String name);
}
