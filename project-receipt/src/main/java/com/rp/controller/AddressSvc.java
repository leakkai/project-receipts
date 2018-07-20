package com.rp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.rp.exception.AddAddressException;
import com.rp.model.Address;
import com.rp.model.AddressReq;
import com.rp.model.GetAddressResponse;
import com.rp.repository.AddressRepo;

@Service
public class AddressSvc {

	@Autowired
	private StoreSvc stoSvc;
	
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
	
	public List<Address> findById(int id) {
		return addRepo.findById(id);
	}
	
	public List<Address> findExactAddress(String street, String city, String postalCode, String state, String country) {
		return addRepo.findExact(country, state, postalCode, city, street);
	}
	
	public List<Address> findByStore(String name) {
		return addRepo.findByStore(name);		
	}
	
	public List<GetAddressResponse> convertToReturnObj(List<Address> addressList) {
		List<GetAddressResponse> addressObj = new ArrayList<GetAddressResponse>();
		
		for (Address a : addressList) {
			GetAddressResponse aReturn = new GetAddressResponse();
			
			Integer id = a.getAddressId();
			String add1 = a.getAddress1();
			String add2 = a.getAddress2();
			String add3 = a.getAddress3();
			String city = a.getCity();
			String zip = a.getPostalCode();
			String state = a.getState();
			String country = a.getCountry();
			
			String addText = new String();
			
			if (null == add1 || add1.isEmpty() ||
				null == city || city.isEmpty() ||
				null == zip || zip.isEmpty() ||
				null == state || state.isEmpty() ||
				null == country || country.isEmpty()) {
				break;
			}
			
			addText = add1;
			
			if (null != add2 && !add2.isEmpty()) {
				addText += ", " + add2;
			}
			
			if (null != add3 && !add3.isEmpty()) {
				addText += ", " + add3;
			}
			
			addText += ", " + city;
			addText += ", " + state;
			addText += " " + zip;
			addText += ", " + country;
			
			aReturn.setId(id);
			aReturn.setAddress(addText);
			
			addressObj.add(aReturn);
		}
		
		return addressObj;
	}

	@Transactional(rollbackFor = Exception.class)
    public String addAddress(@RequestBody AddressReq request) throws Exception {

		String add1 = request.getStreet();
		String city = request.getCity();
		String zip = request.getZip();
		String sta = request.getState();
		String cou = request.getCountry();
		
		String name = request.getStoreName();
		
		if (null == add1 || add1.isEmpty() ||
				null == city || city.isEmpty() ||
				null == zip || zip.isEmpty() ||
				null == sta || sta.isEmpty() ||
				null == cou || cou.isEmpty() ||
				null == name || name.isEmpty()) {
			return "{\"status\":\"empty field(s)\"}";
		}
		
		int id = this.createAddress(add1, city, zip, sta, cou);
		
		if (id < 1) {
//			throw new Exception("{\"status\":\"address fail\"}");
			throw new AddAddressException("address fail");
		}
		
		int stoId = stoSvc.createStore(id, name);
		
		if (stoId < 1) {
			return "{\"status\":\"store fail\"}";
		}
		
        return "{\"status\":\"success\", "
        		+ "\"id\":" + stoId + "}";
    }
	
	@Transactional(rollbackFor = Exception.class)
    public List<GetAddressResponse> getAddress(String storeName) throws Exception {   
        
        if (null == storeName || storeName.isEmpty()) {
        	throw new Exception("Store name is needed while retrieving addresses");
        }
        
        List<Address> storeAddress = this.findByStore(storeName);
        
        return this.convertToReturnObj(storeAddress);
    }
}
