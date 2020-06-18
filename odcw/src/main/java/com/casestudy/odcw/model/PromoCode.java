package com.casestudy.odcw.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "PromoCode")
public class PromoCode extends CarWashObject{

	private String name;
	private String description;
	private String validity;
	private String discount;

}