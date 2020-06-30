package com.casestudy.odcw.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casestudy.odcw.model.PromoCode;
import com.casestudy.odcw.model.dto.PromoCodeDto;
import com.casestudy.odcw.repository.PromoCodeRepository;
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
	
	public List<PromoCodeDto> insertOrEditPromoCode(List<PromoCodeDto> promoCodeDtos) {
		List<PromoCode> promoCodeList = new ArrayList<>();
		PromoCode promocode;
		for(PromoCodeDto dto : promoCodeDtos) {
			if(null == dto.getId()) {
				promocode = new PromoCode();
				promocode.setId(utils.prepareId(promoCodeRepository.findAll().size(), "PC_"));		
		    }else {
		    	Optional<PromoCode> dbpromocode = promoCodeRepository.findById(dto.getId());
		    	promocode = dbpromocode.get();
		    }
			promocode.setName(dto.getName());
			promocode.setDescription(dto.getDescription());
			promocode.setStatus(dto.getStatus());
			promocode.setDiscount(dto.getDiscount());
			promocode.setValidity(dto.getValidity());
			promoCodeList.add(promocode);
		}	
		promoCodeRepository.saveAll(promoCodeList);
		return getAllPromoCodes();
	}
}
