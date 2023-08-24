package com.openclassrooms.service;

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
	public Rental UpdateRental(Rental rental){
		return rentalRepository.save(rental);
	}
	
}
