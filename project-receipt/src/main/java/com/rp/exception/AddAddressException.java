package com.rp.exception;

public class AddAddressException extends Exception {

	private static final long serialVersionUID = 5455511112701481794L;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public AddAddressException() {
		super();
	}
	
	public AddAddressException(String msg) {
		super(msg);
		this.status = msg;
	}
	
}
