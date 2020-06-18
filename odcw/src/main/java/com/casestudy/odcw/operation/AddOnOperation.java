package com.casestudy.odcw.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.casestudy.odcw.model.AddOn;
import com.casestudy.odcw.model.dto.AddOnDto;
import com.casestudy.odcw.repository.AddOnRepository;
import com.casestudy.odcw.util.ODCWConstants;
import com.casestudy.odcw.util.ODCWUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AddOnOperation {

	@Autowired
	private AddOnRepository addOnRepository;
	
	@Autowired
	private ODCWUtils utils;

	public void insertAddOn(AddOnDto addOnDto) {
		
		AddOn addOn = addOnRepository.findByName(addOnDto.getName());
		if(null == addOn) {
			AddOn newAddon = new AddOn();
			newAddon.setId(utils.prepareId(addOnRepository.findAll().size(), "ADDON_"));
			newAddon.setName(addOnDto.getName());
			newAddon.setStatus(ODCWConstants.ACTIVE_STATUS);
			newAddon.setDescription(addOnDto.getDescription());
			newAddon.setCreateDate(new Date());
			newAddon.setCost(addOnDto.getCost());
			addOnRepository.save(newAddon);
			System.out.println("Inserted Successfully");
		}else {
			System.out.println("Already Existing");
		}	
	}
	
	public void updateAddOn(List<AddOnDto> addOnDtosList) {
		
		List<AddOn> list = new ArrayList<>();

		for(AddOnDto addOnDto : addOnDtosList) {
			AddOn dbAddOn = addOnRepository.findByName(addOnDto.getName());
			if(null != dbAddOn) {
				dbAddOn.setName(addOnDto.getName());
				dbAddOn.setStatus(addOnDto.getStatus());
				dbAddOn.setDescription(addOnDto.getDescription());
				dbAddOn.setCreateDate(addOnDto.getCreateDate());
				dbAddOn.setModifiedDate(new Date());
				dbAddOn.setCost(addOnDto.getCost());
				list.add(dbAddOn);
			}
		}
		addOnRepository.saveAll(list);
	}

	public List<AddOnDto> getAllAddOns() {
		List<AddOnDto> addOnDtos = new ArrayList<>();
		List<AddOn> addOns = addOnRepository.findAll();
		addOns.stream().forEach(addOn -> {
			AddOnDto addOnDto = new AddOnDto();
			addOnDto.setName(addOn.getName());
			addOnDto.setDescription(addOn.getDescription());
			addOnDto.setCost(addOn.getCost());
			addOnDto.setId(addOn.getId());
			addOnDto.setStatus(addOn.getStatus());
			addOnDto.setCreateDate(addOn.getCreateDate());
			addOnDto.setModifiedDate(addOn.getModifiedDate());
			addOnDtos.add(addOnDto);
		});
		log.info("AddOn fetched Sucessfully");
		return addOnDtos;
	}

	public List<AddOnDto> activeOrInActiveAddOn(String status){
		List<AddOn> addOns = addOnRepository.findByStatus(status);
		List<AddOnDto> addOnDtos = new ArrayList<>();
		
		if(CollectionUtils.isEmpty(addOns))
			log.error("No " + status +" Addons found!");
		else {
		addOns.stream().forEach(addOn -> {
			AddOnDto addOnDto = new AddOnDto();
			addOnDto.setName(addOn.getName());
			addOnDto.setDescription(addOn.getDescription());
			addOnDto.setCost(addOn.getCost());
			addOnDto.setId(addOn.getId());
			addOnDtos.add(addOnDto);
		});
		log.info("Fetched all " + status +" Addons ");
		}
		return addOnDtos;
	}

}
