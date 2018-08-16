package com.rp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the receipt_header database table.
 * 
 */
@Entity
@Table(name="receipt_header")
@NamedQuery(name="ReceiptHeader.findAll", query="SELECT r FROM ReceiptHeader r")
public class ReceiptHeader implements Serializable {

	private static final long serialVersionUID = -8349476285693887650L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer transactionId;

	private BigDecimal total;

	private BigDecimal grandTotal;
	
	private BigDecimal grandTax;
	
	private BigDecimal tips;
	
	private LocalDateTime createdDate;

	private LocalDateTime date;

	private LocalDateTime lastModDate;

	private String paymentType;

	private Integer storeId;
	

	public ReceiptHeader() {
	}


	public Integer getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}


	public BigDecimal getTotal() {
		return total;
	}


	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	public BigDecimal getGrandTotal() {
		return grandTotal;
	}


	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}


	public BigDecimal getGrandTax() {
		return grandTax;
	}


	public void setGrandTax(BigDecimal grandTax) {
		this.grandTax = grandTax;
	}


	public BigDecimal getTips() {
		return tips;
	}


	public void setTips(BigDecimal tips) {
		this.tips = tips;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public LocalDateTime getLastModDate() {
		return lastModDate;
	}


	public void setLastModDate(LocalDateTime lastModDate) {
		this.lastModDate = lastModDate;
	}


	public String getPaymentType() {
		return paymentType;
	}


	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}


	public Integer getStoreId() {
		return storeId;
	}


	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
}