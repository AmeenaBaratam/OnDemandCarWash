package com.casestudy.odcw.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "ReviewRatings")
public class ReviewRatings {
	
	@Id
	private String id;
	private String customerId;
	private String washerId;
	private float ratings;
	private String reviews;
}
