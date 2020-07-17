package com.casestudy.odcw.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection ="Customer")
@Getter
@Setter
public class Customer extends CarWashObject{

	@DBRef
	private List<CarDetails> carDetails;
	private String email;
	private String phoneNumber;
	private Date lastCarWashDate;
	private String address;
	@DBRef
	private List<ReviewRatings> ratings;
	private String name;
}
