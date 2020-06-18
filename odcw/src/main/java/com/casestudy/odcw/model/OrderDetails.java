package com.casestudy.odcw.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "OrderDetails")
public class OrderDetails extends CarWashObject{
	
	private String customerId;
	@DBRef
	private CarDetails carDetails;
	@DBRef
	private ServicePlans servicePlan;
	private String orderStatus;
	private String washerId;
	@DBRef
	private List<AddOn> addOn;
	@DateTimeFormat(iso = ISO.DATE)
	private Date orderedDate;
	@DateTimeFormat(iso = ISO.DATE)
	private Date carWashDate;
	private String addInfo;
	private String type;
	private double totalCost;
}
