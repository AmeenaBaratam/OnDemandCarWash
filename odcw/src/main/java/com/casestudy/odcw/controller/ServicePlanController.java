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

import com.casestudy.odcw.model.dto.ServicePlanDto;
import com.casestudy.odcw.operation.ServicePlanOperation;
import com.casestudy.odcw.util.ODCWConstants;

@RestController
@RequestMapping("/api/servicePlan")
public class ServicePlanController {

	@Autowired
	private ServicePlanOperation servicePlanOperation;
	
	@PostMapping("/add")
	public ResponseEntity<String> addServicePlan(@RequestBody ServicePlanDto servicePlanDto) {
		servicePlanOperation.addServicePlan(servicePlanDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<String> editServicePlan(@RequestBody List<ServicePlanDto> servicePlanDtoList) {
		servicePlanOperation.editServicePlan(servicePlanDtoList);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{status}")
	public ResponseEntity<List<ServicePlanDto>> activeOrInActiveServicePlans(@PathVariable String status){
		if(ODCWConstants.ACTIVE_STATUS.equalsIgnoreCase(status) || ODCWConstants.INACTIVE_STATUS.equalsIgnoreCase(status))
			return new ResponseEntity<>(servicePlanOperation.activeOrInActiveServicePlans(status),HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ServicePlanDto>> findAll()
	{
		List<ServicePlanDto> servicePlanDtoList = servicePlanOperation.findAllServicePlans();
		return new ResponseEntity<>(servicePlanDtoList,HttpStatus.OK);
	}
}
