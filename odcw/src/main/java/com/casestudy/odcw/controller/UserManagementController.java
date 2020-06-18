package com.casestudy.odcw.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.odcw.model.dto.CustomerDto;
import com.casestudy.odcw.model.dto.ReviewRatingsDto;
import com.casestudy.odcw.model.dto.WasherDto;
import com.casestudy.odcw.operation.CustomerOperations;
import com.casestudy.odcw.operation.WasherOperation;
import com.casestudy.odcw.util.ODCWConstants;

@RestController
@RequestMapping("/api/user")
public class UserManagementController {

	@Autowired
	private WasherOperation washerOperation;
	
	@Autowired
	private CustomerOperations customerOperations;
	
	@GetMapping("/washer/getWasher")
	public ResponseEntity<List<WasherDto>> getAllWashers() {
		return new ResponseEntity<>(washerOperation.getAllWashers(),HttpStatus.OK);
	}
	
	@PostMapping("/washer/addUpdateWasher")
	public ResponseEntity<List<WasherDto>> addOrUpdateWasher(@RequestBody List<WasherDto> washerDtos) {
		return new ResponseEntity<>(washerOperation.addOrUpdateWasher(washerDtos),HttpStatus.OK);
	}
	
	@PostMapping("/customer/addOrUpdate")
	public ResponseEntity<List<CustomerDto>> addOrUpdateCustomer(@RequestBody List<CustomerDto> customerDtos) {
		return new ResponseEntity<>(customerOperations.addOrUpdateCustomer(customerDtos),HttpStatus.OK);
	}
	
	@PostMapping("/customer/rating")
	public ResponseEntity<String> giveRating(@RequestBody ReviewRatingsDto ratingsDto) {
		return new ResponseEntity<>(customerOperations.giveRating(ratingsDto),HttpStatus.OK);
	}
	
	@GetMapping("/washer/{status}")
	public ResponseEntity<List<WasherDto>> activeOrInactiveWasher(@PathVariable String status){
		if(ODCWConstants.ACTIVE_STATUS.equalsIgnoreCase(status) || ODCWConstants.INACTIVE_STATUS.equalsIgnoreCase(status))
			return new ResponseEntity<>(washerOperation.activeOrInactiveWasher(status),HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/washer/review-rating/{washerid}")
	public ResponseEntity<List<ReviewRatingsDto>> viewWasherRating(@PathVariable String washerid) {
		List<ReviewRatingsDto> washerRatings = washerOperation.viewWasherRating(washerid);
		return new ResponseEntity<>(washerRatings, HttpStatus.OK);
	}
	
	@GetMapping("/customer/review-rating/{customerid}")
	public ResponseEntity<List<ReviewRatingsDto>> viewCustomerRating(@PathVariable String customerid) {
		List<ReviewRatingsDto> washerRatings = customerOperations.viewCustomerRating(customerid);
		return new ResponseEntity<>(washerRatings, HttpStatus.OK);
	}
	
	@GetMapping("/customer/{status}")
	public ResponseEntity<List<CustomerDto>> activeOrInactiveCustomer(@PathVariable String status){
		if(ODCWConstants.ACTIVE_STATUS.equalsIgnoreCase(status) || ODCWConstants.INACTIVE_STATUS.equalsIgnoreCase(status))
			return new ResponseEntity<>(customerOperations.activeOrInactiveCustomer(status),HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/washer/download/washerReport.xlsx")
    public ResponseEntity<InputStreamResource> excelWasherReport() throws IOException {
    ByteArrayInputStream in = washerOperation.washerReportToExcelFile();
   
    HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=washerReport.xlsx");
     return ResponseEntity
                  .ok()
                  .headers(headers)
                  .body(new InputStreamResource(in));
    }
	
	@GetMapping("/leaderbord")
	public ResponseEntity<List<WasherDto>> leaderboard() {
		return new ResponseEntity<>(washerOperation.leaderboard(),HttpStatus.OK);
	}
	
}
