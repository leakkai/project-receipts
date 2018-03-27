package com.rp.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The persistent class for the receipt_header database table.
 * 
 */
public class RequestDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private BigDecimal quantity;
	
	private BigDecimal unitPrice;
	
	private BigDecimal price;
	
	private boolean tax;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isTax() {
		return tax;
	}

	public void setTax(boolean tax) {
		this.tax = tax;
	}
	
	
}