package com.openclassrooms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.model.User;
import com.openclassrooms.service.AuthService;
import com.openclassrooms.service.JwtService;

import request.SignUpRequest;
import response.JwtAuthenticationResponse;

@RestController
public class AuthController {    

	@Autowired
	private AuthService authService;
	

	@PostMapping("/auth/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		try {
			User _user = authService.register(user);
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/auth/signup")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
