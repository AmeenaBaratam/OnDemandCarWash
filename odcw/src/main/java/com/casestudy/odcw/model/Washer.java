package com.casestudy.odcw.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "Washer")
@Getter
@Setter
public class Washer extends CarWashObject {
	
	private String emailId;
	@DBRef
	private List<OrderDetails> orderList;
	private long phoneNumber;
	@DBRef
	private List<ReviewRatings> ratings;
	private String name;
	private float overallRating;
}
