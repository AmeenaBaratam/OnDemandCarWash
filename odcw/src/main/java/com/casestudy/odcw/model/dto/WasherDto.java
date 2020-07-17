package com.casestudy.odcw.model.dto;

import java.util.Date;
import java.util.List;

import com.casestudy.odcw.model.OrderDetails;
import com.casestudy.odcw.model.ReviewRatings;

import lombok.Data;

@Data
public class WasherDto {

	private String id;
	private String email;
	private List<OrderDetails> orderList;
	private String phoneNumber;
	private List<ReviewRatings> ratings;
	private Date createDate;
	private Date modifiedDate;
	private String status;
	private String name;
	private float overallRating;
}
