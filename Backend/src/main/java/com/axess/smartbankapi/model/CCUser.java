package com.axess.smartbankapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CCUser {
	
	@Id
	private long ccNumber;
	private String ccName;
	private String userName;
	private String userId;
	private String password;
	private double availableRedeemPoints;
	private double totalRewardsGained;
	public long getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(long ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getCcName() {
		return ccName;
	}
	public void setCcName(String ccName) {
		this.ccName = ccName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getAvailableRedeemPoints() {
		return availableRedeemPoints;
	}
	public void setAvailableRedeemPoints(double availableRedeemPoints) {
		this.availableRedeemPoints = availableRedeemPoints;
	}
	public double getTotalRewardsGained() {
		return totalRewardsGained;
	}
	public void setTotalRewardsGained(double totalRewardsGained) {
		this.totalRewardsGained = totalRewardsGained;
	}
	
	
	
	
	
}
