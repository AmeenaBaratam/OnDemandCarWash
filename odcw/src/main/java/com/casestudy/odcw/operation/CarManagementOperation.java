package com.casestudy.odcw.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.casestudy.odcw.model.CarDetails;
import com.casestudy.odcw.model.dto.CarDetailsDto;
import com.casestudy.odcw.repository.CarDetailsRepository;
import com.casestudy.odcw.util.ODCWConstants;
import com.casestudy.odcw.util.ODCWUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CarManagementOperation {

	@Autowired
	private CarDetailsRepository carDetailsRepository;
	
	@Autowired
	private ODCWUtils utils;

	public void addCar(CarDetailsDto carDetailsDto) {
		
		CarDetails car = carDetailsRepository.findByNumber(carDetailsDto.getNumber());
		if(null == car) {
			car = new CarDetails();
			car.setId(utils.prepareId(carDetailsRepository.findAll().size(), "CAR_"));
			car.setColor(carDetailsDto.getColor());
			car.setName(carDetailsDto.getName());
			car.setStatus(ODCWConstants.ACTIVE_STATUS);
			car.setNumber(carDetailsDto.getNumber());
			car.setCreateDate(new Date());
			carDetailsRepository.save(car);
			System.out.println("Inserted Successfully");
		}else {
			System.out.println("Car details are already Existing");
		}	
	}
	
	public void editCar(List<CarDetailsDto> carDetailsDtoList) {
		
		List<CarDetails> carDetailsList = new ArrayList<>();

		for(CarDetailsDto carDetailsDto : carDetailsDtoList) {
			CarDetails carDetails = carDetailsRepository.findByNumber(carDetailsDto.getNumber());
			if(null != carDetails) {
				carDetails.setColor(carDetailsDto.getColor());
				carDetails.setName(carDetailsDto.getName());
				carDetails.setStatus(carDetailsDto.getStatus());
				carDetails.setNumber(carDetailsDto.getNumber());
				carDetails.setModifiedDate(new Date());
				carDetailsList.add(carDetails);
			}
		}
		carDetailsRepository.saveAll(carDetailsList);
	}

	public List<CarDetailsDto> findAllCarsDetails(){
		List<CarDetailsDto> carDetailsDtoList = new ArrayList<>();
		List<CarDetails> carDetailsDb = carDetailsRepository.findAll();
		carDetailsDb.stream().forEach(carDetails -> {
			CarDetailsDto carDetailsDto = new CarDetailsDto();
			carDetailsDto.setName(carDetails.getName());
			carDetailsDto.setColor(carDetails.getColor());
			carDetailsDto.setStatus(carDetails.getStatus());
			carDetailsDto.setNumber(carDetails.getNumber());
			carDetailsDtoList.add(carDetailsDto);
		});
		return carDetailsDtoList;
	}

	public List<CarDetailsDto> activeOrInActiveCars(String status) {
		List<CarDetails> cars = carDetailsRepository.findByStatus(status);
		List<CarDetailsDto> dtos = new ArrayList<>();
		
		if(CollectionUtils.isEmpty(cars))
			log.error("No " + status +" Cars found!");
		else {
		cars.stream().forEach(carDetails -> {
			CarDetailsDto carDetailsDto = new CarDetailsDto();
			carDetailsDto.setName(carDetails.getName());
			carDetailsDto.setColor(carDetails.getColor());
			carDetailsDto.setNumber(carDetails.getNumber());
			carDetailsDto.setStatus(carDetails.getStatus());
			dtos.add(carDetailsDto);
		});
		log.info("Fetched all " + status +" Cars ");
		}
		return dtos;
	}
}
