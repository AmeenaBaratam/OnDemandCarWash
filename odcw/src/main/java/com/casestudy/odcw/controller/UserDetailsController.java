package com.casestudy.odcw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.odcw.model.User;
import com.casestudy.odcw.model.dto.UserDto;
import com.casestudy.odcw.operation.UserOperations;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserDetailsController {

	@Autowired
	private UserOperations userOperations;
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
		UserDto userResponse = userOperations.login(userDto);
		if (!ObjectUtils.isEmpty(userResponse)) {
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/saveUser")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
		UserDto userResponse = userOperations.createUser(userDto);
		if (!ObjectUtils.isEmpty(userResponse)) {
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
