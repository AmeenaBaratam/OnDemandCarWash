package com.casestudy.odcw.model.dto;

import java.util.Date;
import java.util.List;

import com.casestudy.odcw.model.CarDetails;
import com.casestudy.odcw.model.ReviewRatings;

import lombok.Data;

@Data
public class CustomerDto {

	private String id;
	private List<CarDetails> carDetails;
	private String email;
	private long phoneNumber;
	private Date lastCarWashDate;
	private String address;
	private List<ReviewRatings> ratings;
	private Date createDate;
	private Date modifiedDate;
	private String status;
	private String name;
}
