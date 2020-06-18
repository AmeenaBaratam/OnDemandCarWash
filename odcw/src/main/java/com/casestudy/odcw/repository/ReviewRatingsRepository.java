package com.casestudy.odcw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.ReviewRatings;

public interface ReviewRatingsRepository extends MongoRepository<ReviewRatings, String>{

	ReviewRatings findByCustomerIdAndWasherId(String washerId,String customerId);

}
