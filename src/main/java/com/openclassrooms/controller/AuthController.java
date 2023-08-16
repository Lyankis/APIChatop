package com.openclassrooms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.model.User;
import com.openclassrooms.service.AuthService;

@RestController
public class AuthController {

	@Autowired
	private AuthService authService;

//	@PostMapping("/auth/register")
//	public String registration(@Valid @ModelAttribute("user") User user,
//			BindingResult result,
//			Model model) {
//		User existingUser = authService.findUserByEmail(user.getEmail());
//		
//		if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
//			result.rejectValue("email", null, "Cet email est déjà utilisé !");
//		}
//		
//		if(result.hasErrors()) {
//			model.addAttribute("user", user);
//			return "/register";
//		}
//		
//		authService.register(user);
//		return "redirect:/register?success";
//	}

	@PostMapping("/auth/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		try {
			User _user = authService.register(user);
//			User _user = UserRepository.save(new User(user.getName(), user.getPassword(), false));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
