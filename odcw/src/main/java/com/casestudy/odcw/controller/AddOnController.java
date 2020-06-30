package com.casestudy.odcw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.odcw.model.dto.AddOnDto;
import com.casestudy.odcw.operation.AddOnOperation;

@RestController
@RequestMapping("/api/addon")
@CrossOrigin(origins = "http://localhost:4200")
public class AddOnController {
	
	@Autowired
	private AddOnOperation addOnOperation;
	
	@GetMapping("/all")
	public ResponseEntity<List<AddOnDto>> fetchAllAddOns() {
		List<AddOnDto> addOns = addOnOperation.getAllAddOns();
		return new ResponseEntity<>(addOns, HttpStatus.OK);
	}
	
	@PostMapping("/addOrUpdate")
	public ResponseEntity<List<AddOnDto>> addOrUpdateAddOn(@RequestBody List<AddOnDto> addOnDtosList) {
		List<AddOnDto> addOns = addOnOperation.insertOrUpdateAddOn(addOnDtosList);
		return new ResponseEntity<>(addOns, HttpStatus.OK);
	}
	
	/*@GetMapping("/{status}")
	public ResponseEntity<List<AddOnDto>> activeOrInActiveAddOn(@PathVariable String status){
		if(ODCWConstants.ACTIVE_STATUS.equalsIgnoreCase(status) || ODCWConstants.INACTIVE_STATUS.equalsIgnoreCase(status))
			return new ResponseEntity<>(addOnOperation.activeOrInActiveAddOn(status),HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}*/
}
