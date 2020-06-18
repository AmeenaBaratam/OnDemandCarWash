package com.casestudy.odcw.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "Users")
@Setter
@Getter
public class User{

	@Id
	private String id;
	private String role;
	private String password;
	@Indexed(unique = true)
	private String email;
	private String phoneno;
	private String fullname;
	
}
