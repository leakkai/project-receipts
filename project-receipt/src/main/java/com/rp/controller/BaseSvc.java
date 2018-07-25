package com.rp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.exception.ExceptionMgr;

@Service
public class BaseSvc {

	@Autowired
	ExceptionMgr em;
	
	public BaseSvc() {
	}

	private void checkError() {
		
		if (em.isError()) {
			List<String> err = em.getmessage();
			String conErr = "";
			
			for (String e : err) {
				conErr = conErr.concat(e).concat("\n");
			}
			
			this.clearError();
			
			throw new RuntimeException(conErr);
		}
	}
	
	public void addError(String message) {
		if (null == message || message.isEmpty()) {
			message = "Just error";
		}
		
		em.add(message);
	}
	
	public void throwError(String message) {
		this.addError(message);
		
		this.checkError();
	}
	
	public void throwError() {
		this.checkError();
	}
	
	public void clearError() {
		em.clear();
	}
}
