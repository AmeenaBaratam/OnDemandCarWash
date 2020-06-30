package com.casestudy.odcw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.AddOn;

public interface AddOnRepository extends MongoRepository<AddOn, String>{

}
