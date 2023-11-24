package com.openclassrooms.model;

import java.util.List;

public class Rentals {
	
	public Rentals(List<Rental> listRental) {
		this.rentals = listRental;
	}
	
	List<Rental> rentals;

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}
	
	

}
