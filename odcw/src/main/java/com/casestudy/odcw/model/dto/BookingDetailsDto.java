package com.casestudy.odcw.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BookingDetailsDto {

	private String id;
	private String email;
	private String customer;
	private String address;
	private String category;
	private String brand;
	private String name;
	private String washType;
	private String addOn;
	private String washer;
	private String status;
	private double totalCost;
	private Date orderedDate;
	private Date carWashDate;
}
