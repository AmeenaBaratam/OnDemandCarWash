package com.casestudy.odcw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.ServicePlans;

public interface ServicePlanRepository extends MongoRepository<ServicePlans, String>{

}
