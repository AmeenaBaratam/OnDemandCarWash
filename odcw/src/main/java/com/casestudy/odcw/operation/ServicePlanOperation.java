package com.casestudy.odcw.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.casestudy.odcw.model.ServicePlans;
import com.casestudy.odcw.model.dto.ServicePlanDto;
import com.casestudy.odcw.repository.ServicePlanRepository;
import com.casestudy.odcw.util.ODCWConstants;
import com.casestudy.odcw.util.ODCWUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ServicePlanOperation {

	@Autowired
	private ServicePlanRepository servicePlanRepository;
	
	@Autowired
	private ODCWUtils utils;
	
	public void addServicePlan(ServicePlanDto servicePlanDto) {
		
		ServicePlans plan = servicePlanRepository.findByWashType(servicePlanDto.getWashType());
		if(null == plan) {
			ServicePlans service = new ServicePlans();
			service.setId(utils.prepareId(servicePlanRepository.findAll().size(), "SP_"));
			service.setDescription(servicePlanDto.getDescription());
			service.setStatus(ODCWConstants.ACTIVE_STATUS);
			service.setCreateDate(new Date());
			service.setWashType(servicePlanDto.getWashType());
			service.setWashPackage(servicePlanDto.getWashPackage());
			servicePlanRepository.save(service);
			System.out.println("Inserted Successfully");
		}else {
			System.out.println("Already Existing");
		}	
	}
	public void editServicePlan(List<ServicePlanDto> servicePlanDtoList) {
		
		List<ServicePlans> servicePlanList = new ArrayList<>();

		for(ServicePlanDto servicePlanDto : servicePlanDtoList) {
			ServicePlans service = servicePlanRepository.findByWashType(servicePlanDto.getWashType());
			if(null != service) {
				service.setDescription(servicePlanDto.getDescription());
				service.setStatus(servicePlanDto.getStatus());
				service.setWashType(servicePlanDto.getWashType());
				service.setWashPackage(servicePlanDto.getWashPackage());
				service.setModifiedDate(new Date());
				servicePlanList.add(service);
			}
		}
		servicePlanRepository.saveAll(servicePlanList);
	}

	public List<ServicePlanDto> findAllServicePlans() {
		
		List<ServicePlanDto> servicePlanDtos = new ArrayList<>();
		List<ServicePlans> planList = servicePlanRepository.findAll();
		planList.stream().forEach(plan -> {
			ServicePlanDto planDto = new ServicePlanDto();
			planDto.setPlanId(plan.getId());
			planDto.setDescription(plan.getDescription());
			planDto.setWashPackage(plan.getWashPackage());
			planDto.setWashType(plan.getWashType());
			planDto.setStatus(plan.getStatus());
			servicePlanDtos.add(planDto);
		});
		return servicePlanDtos;
	}
	
	public List<ServicePlanDto> activeOrInActiveServicePlans(String status){
		List<ServicePlans> plans = servicePlanRepository.findByStatus(status);
		List<ServicePlanDto> dtos = new ArrayList<>();
		
		if(CollectionUtils.isEmpty(plans))
			log.error("No " + status +" Service Plans found!");
		else {
		plans.stream().forEach(plan -> {
			ServicePlanDto planDto = new ServicePlanDto();
			planDto.setPlanId(plan.getId());
			planDto.setDescription(plan.getDescription());
			planDto.setWashPackage(plan.getWashPackage());
			planDto.setWashType(plan.getWashType());
			planDto.setStatus(plan.getStatus());
			dtos.add(planDto);
		});
		log.info("Fetched all " + status +" ServicePlans ");
		}
		return dtos;
	}
}
