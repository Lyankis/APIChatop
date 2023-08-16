package com.openclassrooms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.model.User;

import com.openclassrooms.service.UserService;


@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> findUser(@PathVariable("id") Long id) {
		Optional<User> user = userService.findById(id);
		
		if(user.isEmpty()) {
//			return (ResponseEntity<User>) ResponseEntity.badRequest();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Utilisateur inconnu !");
//			return ResponseEntity.badRequest().body("Utilisateur inconu");
		}
		return ResponseEntity.ok(user.get());
	}
}
