package com.rp.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.model.Commovice;
import com.rp.repository.CommoviceRepo;

@Service
public class CommoviceSvc {
	
	@Autowired
	private CommoviceRepo cmRepo;
	
	public Integer create(String name) {
		
		Commovice c = new Commovice();
		LocalDateTime current = LocalDateTime.now();
		
		c.setName(name);
		c.setCreatedDate(current);
		c.setLastModDate(current);
		
		cmRepo.save(c);
		
		if (null == c.getCommoviceId()) {
			//some error for creating this commo
		}
		
		return c.getCommoviceId();
	}
}
