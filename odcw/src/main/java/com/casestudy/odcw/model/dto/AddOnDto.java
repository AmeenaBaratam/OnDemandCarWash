package com.casestudy.odcw.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AddOnDto {
	
	private String id;
	private float cost;
	private String description;
	private String name;
	private String status;
	private Date createDate;
	private Date modifiedDate;
}
