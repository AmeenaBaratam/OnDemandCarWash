package com.casestudy.odcw.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ServicePlanDto {

	private String planId;
	private String description;
	private String washType;
	private String status;
	private double washPackage;
	private Date createDate;
	private Date modifiedDate;
}
