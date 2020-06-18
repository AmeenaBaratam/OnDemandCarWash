package com.casestudy.odcw.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {

	private String id;
	private String role;
	private String password;
	private String email;
	private String phoneno;
	private String fullname;
	private Date createDate;
	private Date modifiedDate;
	private String status;
}
