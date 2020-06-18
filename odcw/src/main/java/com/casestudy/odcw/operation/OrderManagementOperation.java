package com.casestudy.odcw.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.casestudy.odcw.model.AddOn;
import com.casestudy.odcw.model.OrderDetails;
import com.casestudy.odcw.model.ServicePlans;
import com.casestudy.odcw.model.Washer;
import com.casestudy.odcw.model.dto.OrderDetailsDto;
import com.casestudy.odcw.model.dto.WasherDto;
import com.casestudy.odcw.repository.OrderDetailsRepository;
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

	public List<OrderDetailsDto> getOrdersByStatus(String status) {
		List<OrderDetailsDto> ordersDetailsDtos = new ArrayList<>();
		List<OrderDetails> orderDetailsDb = orderDetailsRepository.findByOrderStatus(status);
		for(OrderDetails order : orderDetailsDb) {
			OrderDetailsDto detailsDto = new OrderDetailsDto();
			detailsDto.setId(order.getId());
			detailsDto.setCustomerId(order.getCustomerId());
			detailsDto.setServicePlan(order.getServicePlan());
			detailsDto.setWasherId(order.getWasherId());
			detailsDto.setAddOn(order.getAddOn());
			detailsDto.setCarWashDate(order.getCarWashDate());
			detailsDto.setCarDetails(order.getCarDetails());
			detailsDto.setOrderedDate(order.getOrderedDate());
			detailsDto.setAddInfo(order.getAddInfo());
			detailsDto.setType(order.getType());
			detailsDto.setTotalCost(order.getTotalCost());
			ordersDetailsDtos.add(detailsDto);
		}
		return ordersDetailsDtos;
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
	
	public String bookCarWash(OrderDetailsDto detailsDto) {
		OrderDetails orders = new OrderDetails();
		orders.setId(utils.prepareId(orderDetailsRepository.findAll().size(), "ORDER_"));
		orders.setCarDetails(detailsDto.getCarDetails());
		orders.setAddOn(detailsDto.getAddOn());
		orders.setAddInfo(detailsDto.getAddInfo());
		orders.setServicePlan(detailsDto.getServicePlan());
		orders.setOrderStatus(null!=detailsDto.getWasherId()?ODCWConstants.ASSIGNED:ODCWConstants.PENDING);
		orders.setTotalCost(calculateCost(detailsDto.getAddOn(),detailsDto.getServicePlan()));
		orders.setOrderedDate(new Date());
		orders.setCustomerId(detailsDto.getCustomerId());
		orders.setWasherId(detailsDto.getWasherId());
		orders.setCarWashDate(detailsDto.getCarWashDate());
		orderDetailsRepository.save(orders);
		
		if(null!=detailsDto.getWasherId()) {
			Washer washer = washerRepository.findById(detailsDto.getWasherId()).get();
			List<OrderDetails> ordersList = !CollectionUtils.isEmpty(washer.getOrderList())?washer.getOrderList():new ArrayList<>();
			ordersList.add(orders);
			washer.setOrderList(ordersList);
			washerRepository.save(washer);
		}		
		return "Order Placed";
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
