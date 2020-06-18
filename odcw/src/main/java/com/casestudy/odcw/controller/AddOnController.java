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

import com.casestudy.odcw.model.dto.AddOnDto;
import com.casestudy.odcw.operation.AddOnOperation;
import com.casestudy.odcw.util.ODCWConstants;

@RestController
@RequestMapping("/api/addon")
public class AddOnController {
	@Autowired
	private AddOnOperation addOnOperation;
	
	@GetMapping("/all")
	public ResponseEntity<List<AddOnDto>> fetchAllAddOns() {
		List<AddOnDto> servicePlans = addOnOperation.getAllAddOns();
		return new ResponseEntity<>(servicePlans, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addAddOn(@RequestBody AddOnDto addOnDto) {
		addOnOperation.insertAddOn(addOnDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<String> updateAddOn(@RequestBody List<AddOnDto> addOnDtosList) {
		addOnOperation.updateAddOn(addOnDtosList);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/{status}")
	public ResponseEntity<List<AddOnDto>> activeOrInActiveAddOn(@PathVariable String status){
		if(ODCWConstants.ACTIVE_STATUS.equalsIgnoreCase(status) || ODCWConstants.INACTIVE_STATUS.equalsIgnoreCase(status))
			return new ResponseEntity<>(addOnOperation.activeOrInActiveAddOn(status),HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
