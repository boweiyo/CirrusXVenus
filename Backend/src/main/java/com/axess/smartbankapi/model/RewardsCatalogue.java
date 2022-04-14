package com.axess.smartbankapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class RewardsCatalogue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String item;
	private double redeemptionPoint;
	private double redeemptionAmount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getRedeemptionPoint() {
		return redeemptionPoint;
	}
	public void setRedeemptionPoint(double redeemptionPoint) {
		this.redeemptionPoint = redeemptionPoint;
	}
	public double getRedeemptionAmount() {
		return redeemptionAmount;
	}
	public void setRedeemptionAmount(double redeemptionAmount) {
		this.redeemptionAmount = redeemptionAmount;
	}
	
	
	

}
