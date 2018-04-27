package com.rp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the receipt_header database table.
 * 
 */
public class RequestDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<String> name;

	private List<BigDecimal> quantity;
	
	private List<BigDecimal> unitPrice;
	
	private List<BigDecimal> price;
	
	private boolean[] tax;
	
	private BigDecimal total;
	
	private BigDecimal totalTax;
	
	private BigDecimal tips;
	
	private BigDecimal grandTotal;

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<BigDecimal> getQuantity() {
		return quantity;
	}

	public void setQuantity(List<BigDecimal> quantity) {
		this.quantity = quantity;
	}

	public List<BigDecimal> getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(List<BigDecimal> unitPrice) {
		this.unitPrice = unitPrice;
	}

	public List<BigDecimal> getPrice() {
		return price;
	}

	public void setPrice(List<BigDecimal> price) {
		this.price = price;
	}

	public boolean[] isTax() {
		return tax;
	}

	public void setTax(boolean[] tax) {
		this.tax = tax;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	public BigDecimal getTips() {
		return tips;
	}

	public void setTips(BigDecimal tips) {
		this.tips = tips;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
}