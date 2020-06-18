package com.casestudy.odcw.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.odcw.model.ServicePlans;

public interface ServicePlanRepository extends MongoRepository<ServicePlans, String>{

	List<ServicePlans> findByStatus(String status);
	
	ServicePlans findByWashType(String washType);
}
