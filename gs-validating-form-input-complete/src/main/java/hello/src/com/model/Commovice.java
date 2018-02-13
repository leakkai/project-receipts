package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModDate;

	private String model;

	private String name;

	private String subCategory;

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

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastModDate() {
		return this.lastModDate;
	}

	public void setLastModDate(Date lastModDate) {
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

}