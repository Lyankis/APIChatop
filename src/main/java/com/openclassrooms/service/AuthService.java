package com.openclassrooms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.model.User;
import com.openclassrooms.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User register(User user) {
		
		userRepository.save(user);
		
		return user;
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
  

}
