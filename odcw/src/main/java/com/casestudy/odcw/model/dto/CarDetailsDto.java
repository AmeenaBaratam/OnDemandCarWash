package com.casestudy.odcw.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CarDetailsDto {

	private String color;
	private String status;
	private String name;
	private String number;
	private Date lastCarWashDate;
	private Date createDate;
	private Date modifiedDate;
	private String customerId;
}
