package com.casestudy.odcw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "servicePlans")
@Setter
@Getter
public class ServicePlans extends CarWashObject{
	
	private String description;
	private String washType;
	private String status;
	private double washPackage;
}
