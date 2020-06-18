package com.casestudy.odcw.model.dto;

import lombok.Data;

@Data
public class ReviewRatingsDto {

	private String id;
	private String customerId;
	private String washerId;
	private float ratings;
	private String reviews;
}
