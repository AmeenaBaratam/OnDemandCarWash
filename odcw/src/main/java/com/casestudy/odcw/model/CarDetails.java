package com.casestudy.odcw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "CarDetails")
@Setter
@Getter
public class CarDetails extends CarWashObject{
	
	private String brand;
	private String name;
	private String category;
}
