package com.casestudy.odcw.controller;

import java.util.List;

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

import com.casestudy.odcw.model.dto.BookingDetailsDto;
import com.casestudy.odcw.model.dto.OrderDetailsDto;
import com.casestudy.odcw.operation.OrderManagementOperation;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderManagementController {

	@Autowired
	private OrderManagementOperation orderManagementOperation;
	
	@GetMapping("/all")
	public ResponseEntity<List<OrderDetailsDto>> getAllOrderDetails()
	{
		return new ResponseEntity<>(orderManagementOperation.getAllOrderDetails(),HttpStatus.OK);
	}
	
	@GetMapping("/getOrders/{status}")
	public ResponseEntity<List<BookingDetailsDto>> getOrdersByStatus(@PathVariable String status) {
		return new ResponseEntity<>(orderManagementOperation.getOrdersByStatus(status),HttpStatus.OK);
	}
	
	@PostMapping("/assignOrder")
	public ResponseEntity<String> AssignPendingOrderToCarWasher()
	{
		orderManagementOperation.AssignPendingOrderToCarWasher();
		return new ResponseEntity<>("Assigned Washer",HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> AddOrder(@RequestBody BookingDetailsDto detailsDto)
	{
		return new ResponseEntity<>(orderManagementOperation.bookCarWash(detailsDto),HttpStatus.OK);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<List<BookingDetailsDto>> findOrdersByCustomerId(@PathVariable String id)
	{
		return new ResponseEntity<>(orderManagementOperation.findOrdersByCustomerId(id),HttpStatus.OK);
	}
	
	@GetMapping("/washer/{id}")
	public ResponseEntity<List<BookingDetailsDto>> findOrdersByWasherId(@PathVariable String id)
	{
		return new ResponseEntity<>(orderManagementOperation.findOrdersByWasherId(id),HttpStatus.OK);
	}
	
	@PostMapping("/updateStatus/{email}")
	public ResponseEntity<List<BookingDetailsDto>> updateStatus(@RequestBody List<BookingDetailsDto> detailsDto,@PathVariable String email)
	{
		return new ResponseEntity<>(orderManagementOperation.acceptPendingOrders(detailsDto,email),HttpStatus.OK);
	}
	
	@PostMapping("/complete/{email}")
	public ResponseEntity<List<BookingDetailsDto>> updateCompleteStatus(@RequestBody List<BookingDetailsDto> detailsDto,@PathVariable String email)
	{
		return new ResponseEntity<>(orderManagementOperation.updateCompleteOrders(detailsDto,email),HttpStatus.OK);
	}
}
