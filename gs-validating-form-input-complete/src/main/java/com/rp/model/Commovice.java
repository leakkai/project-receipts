package com.rp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the commovice database table.
 * 
 */
@Entity
@NamedQuery(name="Commovice.findAll", query="SELECT c FROM Commovice c")
public class Commovice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int commoviceId;

	private String brand;

	private String category;

	private LocalDateTime createdDate;

	private String description;

	private LocalDateTime lastModDate;

	private String model;

	private String name;

	private String subCategory;
	
	private int storeId;

	public Commovice() {
	}

	public int getCommoviceId() {
		return this.commoviceId;
	}

	public void setCommoviceId(int commoviceId) {
		this.commoviceId = commoviceId;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getLastModDate() {
		return this.lastModDate;
	}

	public void setLastModDate(LocalDateTime lastModDate) {
		this.lastModDate = lastModDate;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubCategory() {
		return this.subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

}