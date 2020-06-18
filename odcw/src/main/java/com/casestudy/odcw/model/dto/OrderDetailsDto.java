package com.casestudy.odcw.model.dto;

import java.util.Date;
import java.util.List;

import com.casestudy.odcw.model.AddOn;
import com.casestudy.odcw.model.CarDetails;
import com.casestudy.odcw.model.ServicePlans;

import lombok.Data;

@Data
public class OrderDetailsDto {

	private String id;
	private Date createDate;
	private String modifiedDate;
	private String customerId;
	private ServicePlans servicePlan;
	private String orderStatus;
	private String washerId;
	private List<AddOn> addOn;
	private Date carWashDate;
	private CarDetails carDetails;
	private Date orderedDate;
	private String addInfo;
	private String type;
	private double totalCost;
}
