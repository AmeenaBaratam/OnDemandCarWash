package com.casestudy.odcw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.odcw.model.dto.WasherDto;
import com.casestudy.odcw.operation.WasherOperation;

@RestController
@RequestMapping("/api/washers")
@CrossOrigin(origins = "http://localhost:4200")
public class WasherController {

	@Autowired
	private WasherOperation washerOperations;
	
	@PostMapping("/update")
	public ResponseEntity<WasherDto> updateWasher(@RequestBody WasherDto washerDto){
		return new ResponseEntity<>(washerOperations.updateWasherByEmail(washerDto), HttpStatus.OK);
	}
	
	@GetMapping("/get/{email}")
	public ResponseEntity<WasherDto> getWasher(@PathVariable String email){
		return new ResponseEntity<>(washerOperations.findWasherByEmail(email), HttpStatus.OK);
	}
}
