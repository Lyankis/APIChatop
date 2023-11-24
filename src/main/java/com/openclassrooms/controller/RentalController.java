package com.openclassrooms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.model.Rental;
import com.openclassrooms.model.Rentals;
import com.openclassrooms.model.User;
import com.openclassrooms.service.RentalService;
import com.openclassrooms.service.UserService;

import dto.RentalDTO;


@RestController
@RequestMapping("api/")
public class RentalController {
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private UserService userService;
	
	//Recup de la liste des rentals
	@GetMapping("rentals")
	public Rentals getRentals() throws Exception{
		return new Rentals(rentalService.getRentals());
	}
	
	//Recup d'une rental par id
	@GetMapping("/rentals/{id}")
	public ResponseEntity<Object> findRental(@PathVariable("id")Long id) {
		Optional<Rental> rental = rentalService.findById(id);
		
		if(rental.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cette rental n'existe pas !");
		}
		return ResponseEntity.ok(rental.get());
	}
	
	//Création d'une rental
	@PostMapping(value = "/rentals", consumes = "multipart/form-data")
	public ResponseEntity<Rental> createRental(@ModelAttribute RentalDTO rental){		
		try {
			
			SecurityContext securityContext = SecurityContextHolder.getContext();
			
			Authentication authentication = securityContext.getAuthentication();
		
			String username = authentication.getName();
			
			Optional<User> _user = userService.findUserByEmail(username);
			
			rental.setOwner_id(_user.get().getId());
			
			Rental _rental = rentalService.createRental(rental);
			return new ResponseEntity<>(_rental, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@PutMapping("/rentals/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Long id,@RequestBody Rental rental) throws Exception {
		rental.setId(id);
		
        rentalService.UpdateRental(rental);

        return ResponseEntity.ok(rental);
    }

}
