package com.casestudy.odcw.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casestudy.odcw.model.AddOn;
import com.casestudy.odcw.model.dto.AddOnDto;
import com.casestudy.odcw.repository.AddOnRepository;
import com.casestudy.odcw.util.ODCWUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AddOnOperation {

	@Autowired
	private AddOnRepository addOnRepository;
	
	@Autowired
	private ODCWUtils utils;

	public List<AddOnDto> insertOrUpdateAddOn(List<AddOnDto> addOnDtosList) {
		List<AddOn> list = new ArrayList<>();
		AddOn addon;
		for(AddOnDto addOnDto : addOnDtosList) {
			if(null==addOnDto.getId()) {
				addon = new AddOn();
				addon.setId(utils.prepareId(addOnRepository.findAll().size(), "ADDON_"));
			}else {
				Optional<AddOn> dbAddOn = addOnRepository.findById(addOnDto.getId());
				addon = dbAddOn.get();
			}
			addon.setName(addOnDto.getName());
			addon.setStatus(addOnDto.getStatus());
			addon.setDescription(addOnDto.getDescription());
			addon.setCost(addOnDto.getCost());
			list.add(addon);
		}
		addOnRepository.saveAll(list);
		return getAllAddOns();
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
			addOnDtos.add(addOnDto);
		});
		log.info("AddOn fetched Sucessfully");
		return addOnDtos;
	}

	/*public List<AddOnDto> activeOrInActiveAddOn(String status){
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
	}*/

}
