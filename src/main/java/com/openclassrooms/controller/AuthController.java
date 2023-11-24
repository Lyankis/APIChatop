package com.openclassrooms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.model.User;
import com.openclassrooms.service.AuthService;
import com.openclassrooms.service.UserService;

import dto.JwtAuthenticationResponse;
import dto.Response;
import request.SignUpRequest;

@RestController
@RequestMapping("api/")
public class AuthController {    

	@Autowired
	private AuthService authService;
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("auth/register")
	public ResponseEntity<ResponseEntity<Response>> register(@RequestBody User user) {
		return ResponseEntity.ok(authService.register(user));
	}
	
	@PostMapping("auth/login")
    public ResponseEntity<ResponseEntity<JwtAuthenticationResponse>> login(@RequestBody SignUpRequest request) throws Exception {
        return ResponseEntity.ok(authService.login(request));
    }
	
	@GetMapping("auth/me")
	public User getMe() {

		SecurityContext securityContext = SecurityContextHolder.getContext();
		
		Authentication authentication = securityContext.getAuthentication();
	
		String username = authentication.getName();
		
		Optional<User> _user = userService.findUserByEmail(username);
		
		_user.get().setPassword(null);
		
		return _user.get();
	}
}
