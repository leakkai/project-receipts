package com.rp.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExceptionMgr {
	
	private List<String> message = new ArrayList<String>();

	public ExceptionMgr() {
	}
	
	public List<String> getmessage() {
		return message;
	}

	public void setmessage(List<String> message) {
		this.message = message;
	}
	
	public void clear() {
		message = new ArrayList<String>();
	}
	
	public void add(String message) {
		this.message.add(message);
	}
	
	public boolean isError() {
		if (null != this.getmessage() && !this.getmessage().isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
}
