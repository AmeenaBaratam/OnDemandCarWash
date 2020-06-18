package com.casestudy.odcw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.PromoCode;

public interface PromoCodeRepository extends MongoRepository<PromoCode, String>{

	PromoCode findByName(String name);
}
