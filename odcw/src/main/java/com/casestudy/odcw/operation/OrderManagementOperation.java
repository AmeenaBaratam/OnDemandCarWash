package com.casestudy.odcw.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.casestudy.odcw.model.AddOn;
import com.casestudy.odcw.model.CarDetails;
import com.casestudy.odcw.model.Customer;
import com.casestudy.odcw.model.OrderDetails;
import com.casestudy.odcw.model.ServicePlans;
import com.casestudy.odcw.model.Washer;
import com.casestudy.odcw.model.dto.BookingDetailsDto;
import com.casestudy.odcw.model.dto.OrderDetailsDto;
import com.casestudy.odcw.model.dto.WasherDto;
import com.casestudy.odcw.repository.AddOnRepository;
import com.casestudy.odcw.repository.CarDetailsRepository;
import com.casestudy.odcw.repository.CustomerRepository;
import com.casestudy.odcw.repository.OrderDetailsRepository;
import com.casestudy.odcw.repository.ServicePlanRepository;
import com.casestudy.odcw.repository.WasherRepository;
import com.casestudy.odcw.util.ODCWConstants;
import com.casestudy.odcw.util.ODCWUtils;

@Component
public class OrderManagementOperation {

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	private WasherRepository washerRepository;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private AddOnRepository addOnRepo;
	
	@Autowired
	private ServicePlanRepository servicePlanRepo;
	
	@Autowired
	private CarDetailsRepository carDetailsRepo;
	
	@Autowired
	private ODCWUtils utils;
	
	@Autowired 
	private WasherOperation washerOperation;
	
	public List<OrderDetailsDto> getAllOrderDetails() {
		
		List<OrderDetailsDto> list = new ArrayList<>();
		List<OrderDetails> dbList = orderDetailsRepository.findAll();
		for(OrderDetails order : dbList) {
			OrderDetailsDto dto = new OrderDetailsDto();
			dto.setCustomerId(order.getCustomerId());
			dto.setServicePlan(order.getServicePlan());
			dto.setOrderStatus(order.getOrderStatus());
			dto.setWasherId(order.getWasherId());
			dto.setAddOn(order.getAddOn());
			dto.setCarWashDate(order.getCarWashDate());
			dto.setCarDetails(order.getCarDetails());
			dto.setOrderedDate(order.getOrderedDate());
			dto.setAddInfo(order.getAddInfo());
			dto.setType(order.getType());
			dto.setTotalCost(order.getTotalCost());
			list.add(dto);
		}
		return list;
	}

	public List<BookingDetailsDto> getOrdersByStatus(String status) {
		List<BookingDetailsDto> detailsDtos = new ArrayList<>();
		List<OrderDetails> orderDetailsDb = orderDetailsRepository.findByOrderStatus(status);
		for(OrderDetails order : orderDetailsDb) {
			BookingDetailsDto detailsDto = new BookingDetailsDto();
			detailsDto.setId(order.getId());
			detailsDto.setCustomer(order.getCustomerId());
			detailsDto.setWashType(order.getServicePlan().getWashType());
			detailsDto.setAddOn(order.getAddOn().get(0).getName());
			detailsDto.setCarWashDate(order.getCarWashDate());
			detailsDto.setCategory(order.getCarDetails().getCategory());
			detailsDto.setBrand(order.getCarDetails().getBrand());
			detailsDto.setName(order.getCarDetails().getName());
			detailsDto.setOrderedDate(order.getOrderedDate());
			detailsDto.setTotalCost(order.getTotalCost());
			detailsDto.setStatus(order.getOrderStatus());
			detailsDtos.add(detailsDto);
		}
		return detailsDtos;
	}

	public List<BookingDetailsDto> acceptPendingOrders(List<BookingDetailsDto> detailsDto,String email){
		
		if(!ObjectUtils.isEmpty(detailsDto)) {
			for(BookingDetailsDto dto:detailsDto) {
				Washer washer = washerRepository.findByEmail(email);
				Optional<OrderDetails> dbOrder = orderDetailsRepository.findById(dto.getId());
				OrderDetails order = dbOrder.get();
				if(!order.getOrderStatus().equals(dto.getStatus())){
					order.setWasherId(washer.getId());
					order.setOrderStatus(dto.getStatus());
					orderDetailsRepository.save(order);
				}	
			}
		}
		return getOrdersByStatus(ODCWConstants.PENDING);
	}
	public void AssignPendingOrderToCarWasher() {
		List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderStatus(ODCWConstants.PENDING);
		for(OrderDetails order:orderDetailsList)
		{
			Map<String,Integer> washerMap = new HashMap<>();
			List<WasherDto> allWashers = washerOperation.getAllWashers();
			allWashers.stream().forEach(washer -> {
				washerMap.put(washer.getId(),!CollectionUtils.isEmpty(washer.getOrderList())?washer.getOrderList().size():0);
			});
			washerMap.entrySet().stream().sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
	        	.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
			List<String> washerIdList = washerMap.keySet().stream().collect(Collectors.toList());
			
			order.setWasherId(washerIdList.get(0));
			order.setOrderStatus(ODCWConstants.ASSIGNED);
			orderDetailsRepository.save(order);
			
			Washer washer = washerRepository.findById(washerIdList.get(0)).get();
			List<OrderDetails> ordersList = !CollectionUtils.isEmpty(washer.getOrderList())?washer.getOrderList():new ArrayList<>();
		    ordersList.add(order);
			washer.setOrderList(ordersList);
			washerRepository.save(washer);
		}
	}
	
	public String bookCarWash(BookingDetailsDto detailsDto) {
		OrderDetails orders = new OrderDetails();
		orders.setId(utils.prepareId(orderDetailsRepository.findAll().size(), "ORDER_"));
		Customer customer = customerRepo.findByEmail(detailsDto.getEmail());
		CarDetails carDetails = carDetailsRepo.findByBrandAndCategoryAndName(detailsDto.getBrand(), detailsDto.getCategory(), detailsDto.getName());
		orders.setCarDetails(carDetails);
		AddOn addOn = addOnRepo.findByName(detailsDto.getAddOn());
		orders.setAddOn(Arrays.asList(addOn));
		orders.setAddInfo(detailsDto.getAddress());
		ServicePlans plan = servicePlanRepo.findByWashType(detailsDto.getWashType());
		orders.setServicePlan(plan);
		orders.setOrderStatus(null!=detailsDto.getWasher()?ODCWConstants.ASSIGNED:ODCWConstants.PENDING);
		orders.setTotalCost(calculateCost(Arrays.asList(addOn),plan));
		orders.setOrderedDate(new Date());
		orders.setCustomerId(customer.getId());
		orders.setWasherId(detailsDto.getWasher());
		orders.setCarWashDate(detailsDto.getCarWashDate());
		orderDetailsRepository.save(orders);
		
		/*if(null!=detailsDto.getWasherId()) {
			Washer washer = washerRepository.findById(detailsDto.getWasherId()).get();
			List<OrderDetails> ordersList = !CollectionUtils.isEmpty(washer.getOrderList())?washer.getOrderList():new ArrayList<>();
			ordersList.add(orders);
			washer.setOrderList(ordersList);
			washerRepository.save(washer);
		}	*/	
		return "Order Placed";
	}
	
	public List<BookingDetailsDto> findOrdersByCustomerId(String id) {
		
		Customer customer = customerRepo.findByEmail(id);
		List<OrderDetails> orders = orderDetailsRepository.findByCustomerId(customer.getId());
		List<BookingDetailsDto> detailsDto = new ArrayList<>();
		for(OrderDetails order:orders)
		{
			BookingDetailsDto dto = new BookingDetailsDto();
			dto.setId(order.getId());
			dto.setCategory(order.getCarDetails().getCategory());
			dto.setBrand(order.getCarDetails().getBrand());
			dto.setName(order.getCarDetails().getName());
			dto.setWashType(order.getServicePlan().getWashType());
			dto.setAddOn(order.getAddOn().get(0).getName());
			dto.setWasher(order.getWasherId());
			dto.setCarWashDate(order.getCarWashDate());
			dto.setOrderedDate(order.getOrderedDate());
			dto.setStatus(order.getOrderStatus());
			dto.setTotalCost(order.getTotalCost());
			detailsDto.add(dto);
		}
		return detailsDto;
	}
	
	public List<BookingDetailsDto> findOrdersByWasherId(String id) {
		
		Washer washer = washerRepository.findByEmail(id);
		List<OrderDetails> orders = orderDetailsRepository.findByWasherId(washer.getId());
		List<BookingDetailsDto> detailsDto = new ArrayList<>();
		for(OrderDetails order:orders)
		{
			BookingDetailsDto dto = new BookingDetailsDto();
			dto.setId(order.getId());
			dto.setCategory(order.getCarDetails().getCategory());
			dto.setBrand(order.getCarDetails().getBrand());
			dto.setName(order.getCarDetails().getName());
			dto.setWashType(order.getServicePlan().getWashType());
			dto.setAddOn(order.getAddOn().get(0).getName());
			dto.setCustomer(order.getCustomerId());
			dto.setCarWashDate(order.getCarWashDate());
			dto.setOrderedDate(order.getOrderedDate());
			dto.setStatus(order.getOrderStatus());
			dto.setTotalCost(order.getTotalCost());
			detailsDto.add(dto);
		}
		return detailsDto;
	}
	
	public List<BookingDetailsDto> updateCompleteOrders(List<BookingDetailsDto> detailsDto,String email){
		
		if(!ObjectUtils.isEmpty(detailsDto)) {
			for(BookingDetailsDto dto:detailsDto) {
				Washer washer = washerRepository.findByEmail(email);
				Optional<OrderDetails> dbOrder = orderDetailsRepository.findById(dto.getId());
				OrderDetails order = dbOrder.get();
				if(!order.getOrderStatus().equals(dto.getStatus())){
					order.setWasherId(washer.getId());
					order.setOrderStatus(dto.getStatus());
					orderDetailsRepository.save(order);
				}	
			}
		}
		return getOrdersByStatus(ODCWConstants.ASSIGNED);
	}
	private double calculateCost(List<AddOn> addOnList, ServicePlans servicePlan) {
		
		double cost = servicePlan.getWashPackage();
		if(!CollectionUtils.isEmpty(addOnList)) {
			for(AddOn addOn : addOnList)
				cost = addOn.getCost() + cost;
		}
		return cost;
	}
}
