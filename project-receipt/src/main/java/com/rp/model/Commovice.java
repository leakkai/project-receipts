package com.rp.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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

	private static final long serialVersionUID = -327219069358740687L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer commoviceId;

	private String brand;

	private LocalDateTime createdDate;

	private String description;

	private LocalDateTime lastModDate;

	private String model;

	private String name;

	private String tag;
	
	private int storeId;
	
	private String type;

	public Commovice() {
	}

	public Integer getCommoviceId() {
		return this.commoviceId;
	}

	public void setCommoviceId(Integer commoviceId) {
		this.commoviceId = commoviceId;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	//commodity or service
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}