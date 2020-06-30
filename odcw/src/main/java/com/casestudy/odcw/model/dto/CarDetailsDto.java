package com.casestudy.odcw.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CarDetailsDto {

	private String brand;
	private String name;
	private String category;
	private Date createDate;
	private Date modifiedDate;
	private String status;
	private String id;
}
