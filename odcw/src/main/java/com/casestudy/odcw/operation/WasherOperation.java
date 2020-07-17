package com.casestudy.odcw.operation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casestudy.odcw.model.Customer;
import com.casestudy.odcw.model.ReviewRatings;
import com.casestudy.odcw.model.Washer;
import com.casestudy.odcw.model.dto.CustomerDto;
import com.casestudy.odcw.model.dto.ReviewRatingsDto;
import com.casestudy.odcw.model.dto.WasherDto;
import com.casestudy.odcw.repository.WasherRepository;
import com.casestudy.odcw.util.ODCWConstants;
import com.casestudy.odcw.util.ODCWUtils;

@Component
public class WasherOperation {
	
	@Autowired
	private WasherRepository washerRepository;
	
	@Autowired
	private ODCWUtils utils;

	public List<WasherDto> getAllWashers() {
		List<WasherDto> washerDtoList = new ArrayList<>();
		
		List<Washer> washerList = washerRepository.findAll();
		washerList.stream().forEach(washer->{
			WasherDto washerDto = new WasherDto();
			washerDto.setId(washer.getId());
			washerDto.setName(washer.getName());
			washerDto.setOrderList(washer.getOrderList());
			washerDto.setRatings(washer.getRatings());
			washerDto.setPhoneNumber(washer.getPhoneNumber());
			washerDto.setEmail(washer.getEmail());
			washerDtoList.add(washerDto);
		});
		return washerDtoList;
	}
	
	public List<WasherDto> addOrUpdateWasher(List<WasherDto> washerDtoList){
		List<Washer> washerList = new ArrayList<>();
		for(WasherDto washerDto : washerDtoList) {
			Washer washer = washerRepository.findByEmail(washerDto.getEmail());
			if(null == washer) {
				Washer newWasher = new Washer();
				newWasher.setId(utils.prepareId(washerRepository.findAll().size(), "WASHER_"));
				newWasher.setEmail(washerDto.getEmail());
				newWasher.setCreateDate(new Date());
				newWasher.setName(washerDto.getName());
				newWasher.setPhoneNumber(washerDto.getPhoneNumber());
				newWasher.setStatus(ODCWConstants.ACTIVE_STATUS);
				washerList.add(newWasher);
			}else {
				washer.setName(washerDto.getName());
				washer.setOrderList(washerDto.getOrderList());
				washer.setStatus(washerDto.getStatus());
				washer.setPhoneNumber(washerDto.getPhoneNumber());
				washer.setModifiedDate(new Date());
				washerList.add(washer);
			}		
		}
		washerRepository.saveAll(washerList);
		return getAllWashers();
	}
	
	public List<WasherDto> activeOrInactiveWasher(String status) {
		List<Washer> washerList = washerRepository.findByStatus(status);
		List<WasherDto> washerDtoList = new ArrayList<>();
		washerList.stream().forEach(washer->{
			WasherDto washerDto = new WasherDto();
			washerDto.setId(washer.getId());
			washerDto.setName(washer.getName());
			washerDto.setOrderList(washer.getOrderList());
			washerDto.setPhoneNumber(washer.getPhoneNumber());
			washerDto.setRatings(washer.getRatings());
			washerDtoList.add(washerDto);
		});
		
		return washerDtoList;
	}
	
	public List<ReviewRatingsDto> viewWasherRating(String washerid) {
		List<ReviewRatingsDto> reviewRatingDto = new ArrayList<>();
		
		List<ReviewRatings> reviews = washerRepository.findById(washerid).get().getRatings();
		reviews.stream().forEach(reviewsDb -> {
			ReviewRatingsDto ratingDto = new ReviewRatingsDto();
			ratingDto.setCustomerId(reviewsDb.getCustomerId());
			ratingDto.setWasherId(reviewsDb.getWasherId());
			ratingDto.setRatings(reviewsDb.getRatings());
			ratingDto.setReviews(reviewsDb.getReviews());
			reviewRatingDto.add(ratingDto);
		});
		return reviewRatingDto;
	}
	
	public ByteArrayInputStream washerReportToExcelFile() throws IOException{
		
		try(Workbook workbook = new XSSFWorkbook()){
			 List<WasherDto> washers = getAllWashers();
				
			Sheet sheet = workbook.createSheet("Washers");
			
			Row row = sheet.createRow(0);
	     //   CellStyle headerCellStyle = workbook.createCellStyle();
	     //   headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        // Creating header
	        
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Id");
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Name");

	        cell = row.createCell(2);
	        cell.setCellValue("Mobile");
	
	        cell = row.createCell(3);
	        cell.setCellValue("Email");
	        
	        cell = row.createCell(4);
	        cell.setCellValue("Ratings");
	        
	       
			// Creating data rows for each washer
	        for(int i = 0; i < washers.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(washers.get(i).getId());
	        	dataRow.createCell(1).setCellValue(washers.get(i).getName());
	        	dataRow.createCell(2).setCellValue(washers.get(i).getPhoneNumber());
	        	dataRow.createCell(3).setCellValue(washers.get(i).getEmail());
	        	dataRow.createCell(4).setCellValue(washers.get(i).getOverallRating());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        
	       /* FileOutputStream outputStream = new FileOutputStream("WasherReport.xlsx");
	            workbook.write(outputStream);
	        */
	        ByteArrayOutputStream outputStreambyte = new ByteArrayOutputStream();
	        workbook.write(outputStreambyte);
	        return new ByteArrayInputStream(outputStreambyte.toByteArray());
		}
	}
	
	public List<WasherDto> leaderboard(){
		Map<WasherDto,Integer> washerMap = new HashMap<>();
		List<WasherDto> allWashers = getAllWashers();
		allWashers.stream().forEach(washer -> {
			washerMap.put(washer,!CollectionUtils.isEmpty(washer.getOrderList())?washer.getOrderList().size():0);
			
		});
		washerMap.entrySet()
                .stream()
                .sorted((Map.Entry.<WasherDto, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		return washerMap.keySet().stream().collect(Collectors.toList());
	}
	
	public WasherDto findWasherByEmail(String email)
	{
		Washer washer = washerRepository.findByEmail(email);
		WasherDto washerDto = new WasherDto();
		washerDto.setName(washer.getName());
		washerDto.setEmail(washer.getEmail());
		washerDto.setPhoneNumber(washer.getPhoneNumber());
		return washerDto;
	}
	
	public WasherDto updateWasherByEmail(WasherDto washerDto)
	{
		Washer washer = washerRepository.findByEmail(washerDto.getEmail());
		washer.setPhoneNumber(washerDto.getPhoneNumber());
		washer.setName(washerDto.getName());
		washerRepository.save(washer);
		return washerDto;
	}
}
