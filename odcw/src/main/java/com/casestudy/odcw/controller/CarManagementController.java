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

import com.casestudy.odcw.model.dto.CarDetailsDto;
import com.casestudy.odcw.operation.CarManagementOperation;
import com.casestudy.odcw.util.ODCWConstants;

@RestController
@RequestMapping("/api/car")
public class CarManagementController {

	@Autowired
	private CarManagementOperation carManagementOperation;
	
	@PostMapping("/add")
	public ResponseEntity<String> addCar(@RequestBody CarDetailsDto carDetailsDto) {
		carManagementOperation.addCar(carDetailsDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<String> updateCar(@RequestBody List<CarDetailsDto> carDetailsDtoList) {
		carManagementOperation.editCar(carDetailsDtoList);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{status}")
	public ResponseEntity<List<CarDetailsDto>> activeOrInActiveCars(@PathVariable String status){
		if(ODCWConstants.ACTIVE_STATUS.equalsIgnoreCase(status) || ODCWConstants.INACTIVE_STATUS.equalsIgnoreCase(status))
			return new ResponseEntity<>(carManagementOperation.activeOrInActiveCars(status),HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CarDetailsDto>> findAllCars()
	{
		List<CarDetailsDto> carDetailsList = carManagementOperation.findAllCarsDetails();
		return new ResponseEntity<>(carDetailsList,HttpStatus.OK);
	}
}
