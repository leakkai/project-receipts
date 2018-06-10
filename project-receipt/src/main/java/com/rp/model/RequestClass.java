package com.rp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * The persistent class for the receipt_header database table.
 * 
 */
public class RequestClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime date;

	private String storeName;

	private String street;

	private String city;

	private String code;
	
	private String state;
	
	private String country = "US";

	private List<String> name;

	private List<Integer> quantity;
	
	private List<BigDecimal> unitPrice;
	
	private List<BigDecimal> price;
	
	private List<BigDecimal> tax = new ArrayList<BigDecimal>();
	
	private boolean dummyTax;
	
	private BigDecimal total;
	
	private BigDecimal totalTax;
	
	private BigDecimal tips = new BigDecimal(0);
	
	private BigDecimal grandTotal;
	
	private String paymentType;
	
	private String addresses;

	public RequestClass() {
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	
	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(List<Integer> quantity) {
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

	public List<BigDecimal> getTax() {
		return tax;
	}

	public void setTax(List<BigDecimal> tax) {
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

	public boolean isDummyTax() {
		return dummyTax;
	}

	public void setDummyTax(boolean dummyTax) {
		this.dummyTax = dummyTax;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getAddresses() {
		return addresses;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}
}