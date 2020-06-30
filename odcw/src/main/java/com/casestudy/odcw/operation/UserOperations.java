package com.casestudy.odcw.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.casestudy.odcw.model.User;
import com.casestudy.odcw.model.dto.UserDto;
import com.casestudy.odcw.repository.UserRepository;
import com.casestudy.odcw.util.ODCWConstants;
import com.casestudy.odcw.util.ODCWUtils;

@Component
public class UserOperations {

	@Autowired
    private UserRepository userRepository;
	
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
    		user.setStatus(ODCWConstants.ACTIVE_STATUS);
    		userRepository.save(user);
    	}
    	return userDto;
    }
}
