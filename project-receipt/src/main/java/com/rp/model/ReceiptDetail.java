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
 * The persistent class for the receipt_detail database table.
 * 
 */
@Entity
@Table(name="receipt_detail")
@NamedQuery(name="ReceiptDetail.findAll", query="SELECT r FROM ReceiptDetail r")
public class ReceiptDetail implements Serializable {

	private static final long serialVersionUID = -7172941397513381538L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionDetailId;

	private String comment;

	private int commoviceId;

	private LocalDateTime createdDate;

	private LocalDateTime lastModDate;

	private BigDecimal price;

	private int quantity;

	private BigDecimal tax;

	private int transactionId;

	private BigDecimal unitPrice;
	
	private String category;

	public ReceiptDetail() {
	}

	public int getTransactionDetailId() {
		return this.transactionDetailId;
	}

	public void setTransactionDetailId(int transactionDetailId) {
		this.transactionDetailId = transactionDetailId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCommoviceId() {
		return this.commoviceId;
	}

	public void setCommoviceId(int commoviceId) {
		this.commoviceId = commoviceId;
	}

	public LocalDateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(LocalDateTime current) {
		this.createdDate = current;
	}

	public LocalDateTime getLastModDate() {
		return this.lastModDate;
	}

	public void setLastModDate(LocalDateTime lastModDate) {
		this.lastModDate = lastModDate;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public int getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}