package com.casestudy.odcw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.AddOn;

public interface AddOnRepository extends MongoRepository<AddOn, String>{

	Optional<AddOn> findById(String Id);
	
	List<AddOn> findByStatus(String status);

	AddOn findByName(String name);
	
	
}
