package com.casestudy.odcw.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casestudy.odcw.model.CarDetails;
import com.casestudy.odcw.model.dto.CarDetailsDto;
import com.casestudy.odcw.repository.CarDetailsRepository;
import com.casestudy.odcw.util.OdcwConstants;

@Component
public class CarManagementOperation {

	@Autowired
	private CarDetailsRepository carDetailsRepository;

	public void addCar(CarDetailsDto carDetailsDto) {
		
		CarDetails car = carDetailsRepository.findByNumber(carDetailsDto.getNumber());
		if(null == car) {
			car = new CarDetails();
			car.setColor(carDetailsDto.getColor());
			car.setName(carDetailsDto.getName());
			car.setStatus(OdcwConstants.ACTIVE_STATUS);
			car.setNumber(carDetailsDto.getNumber());
			car.setCreateDate(new Date());
			car.setLastCarWashDate(carDetailsDto.getCreateDate());
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
				carDetails.setLastCarWashDate(carDetailsDto.getLastCarWashDate());
				carDetails.setCreateDate(carDetailsDto.getCreateDate());
				carDetails.setCustomerId(carDetailsDto.getCustomerId());
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
			carDetailsDto.setLastCarWashDate(carDetails.getLastCarWashDate());
			carDetailsDto.setCustomerId(carDetails.getCustomerId());
			carDetailsDtoList.add(carDetailsDto);
		});
		return carDetailsDtoList;
	}
	
	public void updateCarStatus(CarDetailsDto carDetailsDto){
		CarDetails dbCar = carDetailsRepository.findByNumber(carDetailsDto.getNumber());
		if(null != dbCar)
		{
			dbCar.setStatus(carDetailsDto.getStatus());
			carDetailsRepository.save(dbCar);
		}
	}
}
