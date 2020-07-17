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

import com.casestudy.odcw.model.dto.CustomerDto;
import com.casestudy.odcw.operation.CustomerOperations;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	private CustomerOperations customerOperations;
	
	@PostMapping("/update")
	public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto){
		return new ResponseEntity<>(customerOperations.updateCustomerByEmail(customerDto), HttpStatus.OK);
	}
	
	@GetMapping("/get/{email}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable String email){
		return new ResponseEntity<>(customerOperations.findCustomerByEmail(email), HttpStatus.OK);
	}
}

