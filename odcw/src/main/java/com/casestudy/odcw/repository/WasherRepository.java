package com.casestudy.odcw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.Washer;

public interface WasherRepository extends MongoRepository<Washer, String>{

	List<Washer> findByStatus(String status);
	
	Washer findByEmailId(String email);
}
