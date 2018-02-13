package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the receipt_detail database table.
 * 
 */
@Entity
@Table(name="receipt_detail")
@NamedQuery(name="ReceiptDetail.findAll", query="SELECT r FROM ReceiptDetail r")
public class ReceiptDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionDetailId;

	private String comment;

	private int commoviceId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModDate;

	private BigDecimal price;

	private int quantity;

	private BigDecimal tax;

	private int transactionId;

	private BigDecimal unitPrice;

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

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModDate() {
		return this.lastModDate;
	}

	public void setLastModDate(Date lastModDate) {
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

}