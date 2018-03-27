package com.rp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.model.Address;
import com.rp.repository.AddressRepo;

@Service
public class AddressSvc {

	@Autowired
	private AddressRepo addRepo;
	
	public int createAddress(String add1, String city, String code, String state, String country) {
		
		Address newAddress = new Address();
		LocalDateTime current = LocalDateTime.now();
		
		newAddress.setAddress1(add1);
		newAddress.setCity(city);
		newAddress.setPostalCode(code);
		newAddress.setState(state);
		newAddress.setCountry(country);
		newAddress.setCreatedDate(current);
		newAddress.setLastModDate(current);
		
		addRepo.save(newAddress);
		
		return newAddress.getAddressId();
	}
	
	public List<Address> findExactAddress(String street, String city, String postalCode, String state, String country) {
		return addRepo.findExact(country, state, postalCode, city, street);
	}
}
