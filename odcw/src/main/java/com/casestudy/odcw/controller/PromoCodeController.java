package com.casestudy.odcw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.odcw.model.dto.PromoCodeDto;
import com.casestudy.odcw.operation.PromoCodeOperation;

@RestController
@RequestMapping("/api/promocode")
public class PromoCodeController {

	@Autowired
	private PromoCodeOperation promoCodeOperation;
	
	@PostMapping("/add")
	public ResponseEntity<String> insertPromoCode(@RequestBody PromoCodeDto promoCodeDto) {
		promoCodeOperation.insertPromoCode(promoCodeDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<String> editPromoCode(@RequestBody List<PromoCodeDto> promoCodeDtoList) {
		promoCodeOperation.editPromoCode(promoCodeDtoList);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PromoCodeDto>> findAll()
	{
		List<PromoCodeDto> promoCodeDtoList = promoCodeOperation.getAllPromoCodes();
		return new ResponseEntity<>(promoCodeDtoList,HttpStatus.OK);
	}
}
