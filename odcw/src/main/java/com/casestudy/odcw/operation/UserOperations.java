package com.casestudy.odcw.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.casestudy.odcw.model.User;
import com.casestudy.odcw.repository.UserRepository;

@Component
public class UserOperations {

	@Autowired
    private UserRepository userRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
