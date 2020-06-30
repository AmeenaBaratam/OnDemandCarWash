package com.casestudy.odcw.model.dto;

import lombok.Data;

@Data
public class UserDto {

	private String id;
	private String role;
	private String password;
	private String email;
	private String phoneno;
	private String fullname;
	private String status;
}
