package com.rp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.model.Commovice;
import com.rp.model.Store;
import com.rp.repository.CommoviceRepo;
import com.rp.repository.StoreRepo;

@Service
public class CommoviceSvc extends BaseSvc {
	
	@Autowired
	private CommoviceRepo cmRepo;
	
	@Autowired
	private StoreRepo stRepo;
	
	public Integer create(String name, Integer storeId) {
		
		Commovice c = new Commovice();
		LocalDateTime current = LocalDateTime.now();
		
		c.setName(name);
		c.setStoreId(storeId);
		c.setCreatedDate(current);
		c.setLastModDate(current);
		
		cmRepo.save(c);
		
		if (null == c.getCommoviceId() || c.getCommoviceId() < 1) {
			this.throwError("Commovice creation error");
		}
		
		return c.getCommoviceId();
	}
	
	public Integer createWithTag(String name, String tag) {
		Commovice c = new Commovice();
		LocalDateTime current = LocalDateTime.now();
		
		c.setName(name);
		c.setTag(tag);
		c.setCreatedDate(current);
		c.setLastModDate(current);
		
		cmRepo.save(c);
		
		if (null == c.getCommoviceId()) {
			//some error for creating this commo
		}
		
		return c.getCommoviceId();
	}
	
	public List<Commovice> getCommoviceByStore(Integer storeId) {
		if (null == storeId) {
			this.throwError("Store ID cannot be null while retrieving commovices");
		}
		
		return cmRepo.findByStoreId(storeId);
	}
	
	public List<Commovice> getItems(String storeName) {
		if (null == storeName || storeName.isEmpty()) {
			return null;
		}
		
		List<Store> storeList = (List<Store>) stRepo.findByName(storeName);
		
		if (null == storeList || storeList.isEmpty()) {
			return null;
		}
		
		Integer id = storeList.get(0).getStoreId();
		
		return this.getCommoviceByStore(id);
	}
}
