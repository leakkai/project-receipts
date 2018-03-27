package com.rp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.model.Store;
import com.rp.repository.StoreRepo;

@Service
public class StoreSvc {

	@Autowired
	private StoreRepo stRepo;
	
	public int createStore(int addId, String storeName) {
		
		Store newStore = new Store();
		LocalDateTime current = LocalDateTime.now();
		
		newStore.setAddressId(addId);
		newStore.setStoreName(storeName);
		newStore.setCreatedDate(current);
		newStore.setLastModDate(current);
		
		stRepo.save(newStore);
		
		return newStore.getStoreId();
	}
	
	public List<Store> findByName(String name) {
		return stRepo.findByName(name);
	}
}
