package com.openclassrooms.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.model.Rental;
import com.openclassrooms.repository.RentalRepository;


@Service
public class RentalService {
	
	@Autowired
	private RentalRepository rentalRepository;
	
	//Recup de la liste des rentals
	public List<Rental> getRentals(){
		return (List<Rental>) rentalRepository.findAll();
	}
	
	//Recup d'une rental par id
	public Optional<Rental> findById(Long id) {
		return rentalRepository.findById(id);
	}
	//Création d'une rental
	public Rental createRental(Rental rental) {
		rentalRepository.save(rental);
		
		return rental;
	}
	
	//Update d'une rental
	public Rental UpdateRental(Rental rental) throws Exception{
		
		Long time = Date.from(Instant.now()).getTime();
		
		Rental updateRental = findById(rental.getId())
                .orElseThrow(() -> new Exception("Aucune rental trouvé avec cette id: " + rental.getId()));

        updateRental.setName(rental.getName());
        updateRental.setSurface(rental.getSurface());
        updateRental.setPrice(rental.getPrice());
        updateRental.setPicture(rental.getPicture());
        updateRental.setDescription(rental.getDescription());
        
        
        
        updateRental.setCreated_at(new java.sql.Date(time));
        updateRental.setUpdated_at(new java.sql.Date(time));

		
		return rentalRepository.save(rental);
	}
	
}
