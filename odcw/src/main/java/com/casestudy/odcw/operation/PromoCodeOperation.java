package com.casestudy.odcw.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casestudy.odcw.model.PromoCode;
import com.casestudy.odcw.model.dto.PromoCodeDto;
import com.casestudy.odcw.repository.PromoCodeRepository;
import com.casestudy.odcw.util.ODCWConstants;
import com.casestudy.odcw.util.ODCWUtils;

@Component
public class PromoCodeOperation {

	@Autowired
	private PromoCodeRepository promoCodeRepository;
	
	@Autowired
	private ODCWUtils utils;
	
	public List<PromoCodeDto> getAllPromoCodes() {
		List<PromoCodeDto> promoCodeDtos = new ArrayList<>();
		List<PromoCode> promoCodeDbList = promoCodeRepository.findAll();
		for(PromoCode promoCode : promoCodeDbList) {
			PromoCodeDto promoCodeNew = new PromoCodeDto();
			promoCodeNew.setId(promoCode.getId());
			promoCodeNew.setDescription(promoCode.getDescription());
			promoCodeNew.setName(promoCode.getName());
			promoCodeNew.setDiscount(promoCode.getDiscount());
			promoCodeNew.setValidity(promoCode.getValidity());
			promoCodeNew.setStatus(promoCode.getStatus());
			promoCodeDtos.add(promoCodeNew);
		}
		return promoCodeDtos;
	}
	
	public void insertPromoCode(PromoCodeDto promoCodeDto) {
		
		PromoCode code = promoCodeRepository.findByName(promoCodeDto.getName());
		if(null == code) {
			code = new PromoCode();
			code.setId(utils.prepareId(promoCodeRepository.findAll().size(), "PC_"));
			code.setName(promoCodeDto.getName());
			code.setDescription(promoCodeDto.getDescription());
			code.setStatus(ODCWConstants.ACTIVE_STATUS);
			code.setCreateDate(new Date());
			code.setDiscount(promoCodeDto.getDiscount());
			code.setValidity(promoCodeDto.getValidity());
			promoCodeRepository.save(code);
			System.out.println("Inserted Successfully");
		}else {
			System.out.println("Already Existing");
		}	
	}
	public void editPromoCode(List<PromoCodeDto> promoCodeDtoList) {
		
		List<PromoCode> promoCodeList = new ArrayList<>();

		for(PromoCodeDto dto : promoCodeDtoList) {
			PromoCode promocode = promoCodeRepository.findByName(dto.getName());
			if(null!=promocode) {
				promocode.setDescription(dto.getDescription());
				promocode.setStatus(dto.getStatus());
				promocode.setDiscount(dto.getDiscount());
				promocode.setValidity(dto.getValidity());
				promocode.setModifiedDate(new Date());
				promoCodeList.add(promocode);
			}
		}
		promoCodeRepository.saveAll(promoCodeList);
	}
}
