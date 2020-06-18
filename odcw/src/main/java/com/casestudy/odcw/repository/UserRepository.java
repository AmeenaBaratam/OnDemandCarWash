package com.casestudy.odcw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	User findByEmail(String email);

}
