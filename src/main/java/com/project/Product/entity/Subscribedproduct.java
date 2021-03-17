package com.project.Product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.Product.dto.ProductDTO;

@Entity
@Table(name = "subscribedproduct")
public class Subscribedproduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int subid;
	@Column(nullable = false)
	int buyerid;
	@Column(nullable = false)
	int prodid;
	@Column(nullable = false)
	int quantity;
	
	//constructors
	public Subscribedproduct() {
		super();
	}
	
	public Subscribedproduct(int subid, int buyerid, int prodid, int quantity) {
		super();
		this.subid = subid;
		this.buyerid = buyerid;
		this.prodid = prodid;
		this.quantity = quantity;
	}

	//getters and setters
	public int getSubid() {
		return subid;
	}

	public void setSubid(int subid) {
		this.subid = subid;
	}

	public int getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
