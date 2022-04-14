package com.axess.smartbankapi.dto;

import java.util.List;

import com.axess.smartbankapi.model.RewardsCatalogue;

public class UserRedeemptionHistoryDto {
	
	
	private List<RewardsCatalogue> itemsRedeemed;
	private int quantity;
	private long ccNumber;
	private double totalPointsRedeemed;
	private double totalAmountGained;
	public List<RewardsCatalogue> getItemsRedeemed() {
		return itemsRedeemed;
	}
	public void setItemsRedeemed(List<RewardsCatalogue> itemsRedeemed) {
		this.itemsRedeemed = itemsRedeemed;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(long ccNumber) {
		this.ccNumber = ccNumber;
	}
	public double getTotalPointsRedeemed() {
		return totalPointsRedeemed;
	}
	public void setTotalPointsRedeemed(double totalPointsRedeemed) {
		this.totalPointsRedeemed = totalPointsRedeemed;
	}
	public double getTotalAmountGained() {
		return totalAmountGained;
	}
	public void setTotalAmountGained(double totalAmountGained) {
		this.totalAmountGained = totalAmountGained;
	}
	
	

}
