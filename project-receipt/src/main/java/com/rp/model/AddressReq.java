package com.rp.model;

import java.io.Serializable;

/**
 * The persistent class for the receipt_header database table.
 * 
 */
public class AddressReq implements Serializable {
	private static final long serialVersionUID = 1L;

	private String storeName;

	private String street;

	private String city;

	private String zip;
	
	private String state = "Texas";
	
	private String country = "US";

	public AddressReq() {
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}