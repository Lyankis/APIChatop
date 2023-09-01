package com.openclassrooms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.model.Role;
import com.openclassrooms.model.User;
import com.openclassrooms.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import request.SignUpRequest;
import request.SigninRequest;
import response.JwtAuthenticationResponse;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	@Autowired
    private JwtService jwtService;
	
    private AuthenticationManager authenticationManager;
	
	public User register(User user) {
		
		userRepository.save(user);
		
		return user;
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
  
	
	    
	    public JwtAuthenticationResponse login(SignUpRequest request) {
	    	
	    	User user = new User();
	    	user.setEmail(request.getEmail());
	    	user.setPassword(passwordEncoder.encode(request.getPassword()));
	    	user.setRole(Role.USER);
	    	String jwt = jwtService.generateToken(user);
	    	return new JwtAuthenticationResponse(jwt);
	    }

}
