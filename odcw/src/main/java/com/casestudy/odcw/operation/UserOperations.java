package com.casestudy.odcw.operation;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.casestudy.odcw.model.User;
import com.casestudy.odcw.model.dto.CustomerDto;
import com.casestudy.odcw.model.dto.UserDto;
import com.casestudy.odcw.model.dto.WasherDto;
import com.casestudy.odcw.repository.UserRepository;
import com.casestudy.odcw.util.ODCWUtils;

@Component
public class UserOperations {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private CustomerOperations customerOperations;
	
	@Autowired
	private WasherOperation washerOperation;
	
	@Autowired
	private ODCWUtils utils;

	public UserDto login(UserDto userObj) {
		User user = userRepository.findByEmail(userObj.getEmail());
		if(!ObjectUtils.isEmpty(user)){
			UserDto userDto = new UserDto();
			userDto.setEmail(user.getEmail());
			userDto.setFullname(user.getFullname());
			userDto.setId(user.getId());
			userDto.setPassword(user.getPassword());
			userDto.setPhoneno(user.getPhoneno());
			userDto.setRole(user.getRole());
			userDto.setStatus(user.getStatus());
			return userDto;
		}else {
			return new UserDto();
		}
	}

    public UserDto createUser(UserDto userDto){
    	User user = userRepository.findByEmail(userDto.getEmail());
    	if(ObjectUtils.isEmpty(user)) {
    		user = new User();
    		user.setEmail(userDto.getEmail());
    		user.setFullname(userDto.getFullname());
    		user.setId(utils.prepareId(userRepository.findAll().size(), "USER_"));
    		user.setPhoneno(userDto.getPhoneno());
    		user.setRole(userDto.getRole());
    		user.setPassword(userDto.getPassword());
    		user.setStatus(userDto.getStatus());
    		userRepository.save(user);
    		if(userDto.getRole().equals("customer")) {
    			CustomerDto customer = new CustomerDto();
    			customer.setEmail(userDto.getEmail());
    			customer.setName(userDto.getFullname());
    			customer.setPhoneNumber(userDto.getPhoneno());
    			customer.setStatus(userDto.getStatus());
    			customerOperations.addOrUpdateCustomer(Arrays.asList(customer));
    		}
    		if(userDto.getRole().equals("washer")) {
    			WasherDto washer = new WasherDto();
    			washer.setEmail(userDto.getEmail());
    			washer.setName(userDto.getFullname());
    			washer.setPhoneNumber(userDto.getPhoneno());
    			washer.setStatus(userDto.getStatus());
    			washerOperation.addOrUpdateWasher(Arrays.asList(washer));
    		}
    	}
    	return userDto;
    }
}
