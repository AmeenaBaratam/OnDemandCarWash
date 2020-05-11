package com.casestudy.odcw.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casestudy.odcw.model.ServicePlans;
import com.casestudy.odcw.model.dto.ServicePlanDto;
import com.casestudy.odcw.repository.ServicePlanRepository;
import com.casestudy.odcw.util.OdcwConstants;

@Component
public class ServicePlanManagement {

	@Autowired
	private ServicePlanRepository servicePlanRepository;
	
	public void addServicePlan(ServicePlanDto servicePlanDto) {
		
		Optional<ServicePlans> plan = servicePlanRepository.findById(servicePlanDto.getPlanId());
		if(!plan.isPresent()) {
			ServicePlans service = new ServicePlans();
			service.setDescription(servicePlanDto.getDescription());
			service.setStatus(OdcwConstants.ACTIVE_STATUS);
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
			Optional<ServicePlans> plan = servicePlanRepository.findById(servicePlanDto.getPlanId());
			if(plan.isPresent()) {
				ServicePlans service = plan.get();
				service.setDescription(servicePlanDto.getDescription());
				service.setStatus(servicePlanDto.getStatus());
				service.setCreateDate(servicePlanDto.getCreateDate());
				service.setWashType(servicePlanDto.getWashType());
				service.setWashPackage(servicePlanDto.getWashPackage());
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
			planDto.setDescription(plan.getDescription());
			planDto.setWashPackage(plan.getWashPackage());
			planDto.setWashType(plan.getWashType());
			planDto.setStatus(plan.getStatus());
			servicePlanDtos.add(planDto);
		});
		return servicePlanDtos;
	}
	
	public void updateStatus(ServicePlanDto servicePlanDto){
		Optional<ServicePlans> plan = servicePlanRepository.findById(servicePlanDto.getPlanId());
		if(null != plan)
		{
			ServicePlans service = plan.get();
			service.setStatus(servicePlanDto.getStatus());
			servicePlanRepository.save(service);
		}
	}
}
