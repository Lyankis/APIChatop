package com.openclassrooms.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.model.Rental;
import com.openclassrooms.repository.RentalRepository;

import dto.RentalDTO;

@Service
public class RentalService {
	
	@Autowired
	private RentalRepository rentalRepository;
	
	//Recup de la liste des rentals
	public List<Rental> getRentals() throws Exception{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null) {
	        throw new Exception("Utilisateur non authentifié");
	    }
		return (List<Rental>) rentalRepository.findAll();
	}
	
	//Verif par id de l'existance d'une rental
	public Optional<Rental> findById(Long id) {
		return rentalRepository.findById(id);
	}
	
	//Création d'une rental
	public Rental createRental(RentalDTO rental) throws IOException {

		Long time = Date.from(Instant.now()).getTime();
		
		Rental newRental = new Rental();
		
		newRental.setName(rental.getName());
		newRental.setSurface(rental.getSurface());
		newRental.setPrice(rental.getPrice());
		
//		newRental.setPicture(rental.getPicture().getOriginalFilename());
		
		String filename = uploadPicture(rental);
		newRental.setPicture(filename);
		
		newRental.setDescription(rental.getDescription());
		
		newRental.setOwner_id(rental.getOwner_id());
		
		newRental.setCreated_at(new Date(time));
		newRental.setUpdated_at(new Date(time));
		
		return rentalRepository.save(newRental);
	}
	
	public String uploadPicture(RentalDTO rental) throws IOException {
		
	    // Récupération de l'image du RentalDTO
	    MultipartFile imageFile = rental.getPicture();

	    // Génération d'un filename unique 
	    String filename = String.valueOf(System.currentTimeMillis()) + "_" + imageFile.getOriginalFilename();

	    Path path = Paths.get("src/main/resources/static/images/", filename);
        Files.write(path, imageFile.getBytes());

	    return "/images/" + filename;
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
        
        
        
        updateRental.setCreated_at(new Date(time));
        updateRental.setUpdated_at(new Date(time));

		
		return rentalRepository.save(rental);
	}
	
}
