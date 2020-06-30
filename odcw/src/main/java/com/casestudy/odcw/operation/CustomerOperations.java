package com.casestudy.odcw.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casestudy.odcw.model.Customer;
import com.casestudy.odcw.model.ReviewRatings;
import com.casestudy.odcw.model.Washer;
import com.casestudy.odcw.model.dto.CustomerDto;
import com.casestudy.odcw.model.dto.ReviewRatingsDto;
import com.casestudy.odcw.repository.CustomerRepository;
import com.casestudy.odcw.repository.ReviewRatingsRepository;
import com.casestudy.odcw.repository.WasherRepository;
import com.casestudy.odcw.util.ODCWConstants;
import com.casestudy.odcw.util.ODCWUtils;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerOperations {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ReviewRatingsRepository ratingsRepository;
	
	@Autowired
	private ODCWUtils utils;
	
	@Autowired 
	private CarManagementOperation carManagementOperation;
	
	@Autowired
	private WasherRepository washerRepository;
	
	public List<ReviewRatingsDto> viewCustomerRating(String customerid) {
		List<ReviewRatingsDto> reviewRatingsDto = new ArrayList<>();
		
		List<ReviewRatings> reviews = customerRepository.findById(customerid).get().getRatings();
		reviews.stream().forEach(reviewsDb -> {
			ReviewRatingsDto ratingDto = new ReviewRatingsDto();
			ratingDto.setCustomerId(reviewsDb.getCustomerId());
			ratingDto.setWasherId(reviewsDb.getWasherId());
			ratingDto.setRatings(reviewsDb.getRatings());
			ratingDto.setReviews(reviewsDb.getReviews());
			reviewRatingsDto.add(ratingDto);
		});
		log.info("Customer Ratings fetched successfully.");
		return reviewRatingsDto;
	}

	public List<CustomerDto> activeOrInactiveCustomer(String status) {
		List<Customer> customerList = customerRepository.findByStatus(status);
		List<CustomerDto> customerDtoList = new ArrayList<>();
		customerList.stream().forEach(customer->{
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setCarDetails(customer.getCarDetails());
			customerDto.setEmail(customer.getEmail());
			customerDto.setPhoneNumber(customer.getPhoneNumber());
			customerDto.setLastCarWashDate(customer.getLastCarWashDate());
			customerDto.setAddress(customer.getAddress());
			customerDto.setRatings(customer.getRatings());
			customerDtoList.add(customerDto);
		});
		
		return customerDtoList;
	}
	
	public List<CustomerDto> findAll()
	{
		List<CustomerDto> customerList = new ArrayList<>();
		List<Customer> dbCustomersList = customerRepository.findAll();
		dbCustomersList.stream().forEach(customer->{
			CustomerDto dto = new CustomerDto();
			dto.setId(customer.getId());
			dto.setName(customer.getName());
			dto.setCarDetails(customer.getCarDetails());
			dto.setEmail(customer.getEmail());
			dto.setPhoneNumber(customer.getPhoneNumber());
			dto.setLastCarWashDate(customer.getLastCarWashDate());
			dto.setAddress(customer.getAddress());
			dto.setStatus(customer.getStatus());
			dto.setRatings(customer.getRatings());
			customerList.add(dto);
		});
		return customerList;
	}
	
	public List<CustomerDto> addOrUpdateCustomer(List<CustomerDto> customerDtoList)
	{
		List<Customer> customersList = new ArrayList<>();
		for(CustomerDto dto : customerDtoList)
		{
			Customer customer = customerRepository.findByEmail(dto.getEmail());
			if(null == customer)
			{
				Customer newCustomer = new Customer();
				newCustomer.setId(utils.prepareId(customerRepository.findAll().size(), "CUST_"));
				newCustomer.setAddress(dto.getAddress());
				newCustomer.setCreateDate(new Date());
				newCustomer.setPhoneNumber(dto.getPhoneNumber());
				newCustomer.setEmail(dto.getEmail());
				newCustomer.setName(dto.getName());
				newCustomer.setStatus(ODCWConstants.ACTIVE_STATUS);
				customersList.add(newCustomer);
			}else {
				customer.setAddress(dto.getAddress());
				customer.setCreateDate(new Date());
				customer.setPhoneNumber(dto.getPhoneNumber());
				customer.setName(dto.getName());
				customer.setStatus(dto.getStatus());
				customersList.add(customer);
			}
		}
		customerRepository.saveAll(customersList);
		return findAll();
	}
	
	public String giveRating(ReviewRatingsDto ratingsDto)
	{
		float total=0;
		Customer customer = customerRepository.findById(ratingsDto.getCustomerId()).get();
		Washer washer = washerRepository.findById(ratingsDto.getWasherId()).get();

		ReviewRatings rating = new ReviewRatings();
		rating.setCustomerId(ratingsDto.getCustomerId());
		rating.setWasherId(ratingsDto.getWasherId());
		rating.setRatings(ratingsDto.getRatings());
		rating.setReviews(ratingsDto.getReviews());
		ratingsRepository.save(rating);
		
		List<ReviewRatings> custRatings = (null != customer.getRatings()?customer.getRatings():new ArrayList<>());
		custRatings.add(rating);
		customer.setRatings(custRatings);
		customerRepository.save(customer);
		
		List<ReviewRatings> washerRatings = (null != washer.getRatings()?washer.getRatings():new ArrayList<>());
		washerRatings.add(rating);
		washer.setRatings(washerRatings);
		for(ReviewRatings value:washerRatings)
		{
			total = total+value.getRatings();
		}
		washer.setOverallRating(total/washerRatings.size());
		washerRepository.save(washer);
		return "";
	}
	
	
}
