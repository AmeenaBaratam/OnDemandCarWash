package com.casestudy.odcw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	User findByEmail(String email);
	
	List<User> findByRole(String role);

}
