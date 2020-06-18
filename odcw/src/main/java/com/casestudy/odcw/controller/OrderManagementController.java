package com.casestudy.odcw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.odcw.model.dto.OrderDetailsDto;
import com.casestudy.odcw.operation.OrderManagementOperation;

@RestController
@RequestMapping("/api/orders")
public class OrderManagementController {

	@Autowired
	private OrderManagementOperation orderManagementOperation;
	
	@GetMapping("/all")
	public ResponseEntity<List<OrderDetailsDto>> getAllOrderDetails()
	{
		return new ResponseEntity<>(orderManagementOperation.getAllOrderDetails(),HttpStatus.OK);
	}
	
	@GetMapping("/getOrders/{status}")
	public ResponseEntity<List<OrderDetailsDto>> getOrdersByStatus(@PathVariable String status) {
		return new ResponseEntity<>(orderManagementOperation.getOrdersByStatus(status),HttpStatus.OK);
	}
	
	@PostMapping("/assignOrder")
	public ResponseEntity<String> AssignPendingOrderToCarWasher()
	{
		orderManagementOperation.AssignPendingOrderToCarWasher();
		return new ResponseEntity<>("Assigned Washer",HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> AddOrder(@RequestBody OrderDetailsDto orderDetailsDto)
	{
		return new ResponseEntity<>(orderManagementOperation.bookCarWash(orderDetailsDto),HttpStatus.OK);
	}
}
