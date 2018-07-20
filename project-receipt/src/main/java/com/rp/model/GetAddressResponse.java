package com.rp.model;

import java.io.Serializable;

/**
 * Request holder for get address.
 * 
 */
public class GetAddressResponse extends Object implements Serializable {

	private static final long serialVersionUID = -6632310029338568711L;

	private Integer id;

	private String address;

	public GetAddressResponse() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}