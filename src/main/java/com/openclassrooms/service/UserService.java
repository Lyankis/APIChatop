package com.openclassrooms.service;

import java.util.Optional;
import com.openclassrooms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
