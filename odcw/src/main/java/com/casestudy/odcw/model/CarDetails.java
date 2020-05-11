package com.casestudy.odcw.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "car")
@Setter
@Getter
public class CarDetails extends CarWashObject{
	
	private String color;
	private String status;
	private String name;
	private String number;
	private String customerId;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date lastCarWashDate;
}
