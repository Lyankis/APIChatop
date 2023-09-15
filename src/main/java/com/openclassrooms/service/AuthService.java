package com.openclassrooms.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.model.Role;
import com.openclassrooms.model.User;
import com.openclassrooms.repository.UserRepository;

import request.SignUpRequest;
import response.JwtAuthenticationResponse;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
    private JwtService jwtService;
	
//    private AuthenticationManager authenticationManager;
	
	public User register(User user) {
		
		Long time = Date.from(Instant.now()).getTime();
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
//		newUser.setRole(Role.USER);
		newUser.setCreated_at(new Date(time));
		newUser.setUpdated_at(new Date(time));
		
		return userRepository.save(newUser);
		
		
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		
//		userRepository.save(user);
//		
//		return user;
	}
	
	public Optional<User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
  
	
	    
	    public JwtAuthenticationResponse login(SignUpRequest request) {
	    	
	    	User user = new User();
	    	user.setEmail(request.getEmail());
	    	user.setPassword(passwordEncoder.encode(request.getPassword()));
//	    	user.setRole(Role.USER);
	    	String jwt = jwtService.generateToken(user);
	    	return new JwtAuthenticationResponse(jwt);
	    }

}
