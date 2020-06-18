package com.casestudy.odcw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "AddOn")
public class AddOn extends CarWashObject {
	
	private String name;
	private String description;
	private float cost;

}
