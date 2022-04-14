package com.axess.smartbankapi.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class UserRedeemptionHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne
	private RewardsCatalogue catalogue;
	private int quantity;
	@OneToOne
	private CCUser ccUser;
	private LocalDate orderdate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public RewardsCatalogue getCatalogue() {
		return catalogue;
	}
	public void setCatalogue(RewardsCatalogue catalogue) {
		this.catalogue = catalogue;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CCUser getCcUser() {
		return ccUser;
	}
	public void setCcUser(CCUser ccUser) {
		this.ccUser = ccUser;
	}
	public LocalDate getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}
	
	

}
