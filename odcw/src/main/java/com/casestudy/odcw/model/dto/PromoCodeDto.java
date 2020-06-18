package com.casestudy.odcw.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PromoCodeDto {

	private String id;
	private String name;
	private String description;
	private String status;
	private String validity;
	private String discount;
	private Date createDate;
	private Date modifiedDate;
}
