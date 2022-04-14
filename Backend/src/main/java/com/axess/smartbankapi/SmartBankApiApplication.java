package com.axess.smartbankapi;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.axess.smartbankapi.model.CCUser;
import com.axess.smartbankapi.model.RewardsCatalogue;
import com.axess.smartbankapi.service.CCUserService;
import com.axess.smartbankapi.service.RewardCatalogueService;

@SpringBootApplication
public class SmartBankApiApplication implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SmartBankApiApplication.class);

	@Autowired
	private CCUserService userService;
	@Autowired
	private RewardCatalogueService rcService;

	public static void main(String[] args) {
		SpringApplication.run(SmartBankApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		
		LOGGER.info(this.userService.saveAllUsers(this.loadUsersData()));
		LOGGER.info(this.rcService.saveAllItems(this.loadCatalogueData()));

	}
	
	private List<RewardsCatalogue> loadCatalogueData() {
		List<RewardsCatalogue> catalogueItems = new ArrayList<>();
		
		RewardsCatalogue item = new RewardsCatalogue();
		item.setItem("Amazon Voucher");
		item.setRedeemptionAmount(500);
		item.setRedeemptionPoint(1500);
		catalogueItems.add(item);
		
		item = new RewardsCatalogue();
		item.setItem("Amazon Voucher");
		item.setRedeemptionAmount(750);
		item.setRedeemptionPoint(2500);
		catalogueItems.add(item);
		
		item = new RewardsCatalogue();
		item.setItem("Starbucks Voucher");
		item.setRedeemptionAmount(350);
		item.setRedeemptionPoint(1000);
		catalogueItems.add(item);
		
		item = new RewardsCatalogue();
		item.setItem("Walmart Voucher");
		item.setRedeemptionAmount(500);
		item.setRedeemptionPoint(1500);
		catalogueItems.add(item);
		
		item = new RewardsCatalogue();
		item.setItem("Uber Voucher");
		item.setRedeemptionAmount(500);
		item.setRedeemptionPoint(1500);
		catalogueItems.add(item);
		
		
		return catalogueItems;
	}

	private List<CCUser> loadUsersData() {
		List<CCUser> users = new ArrayList<CCUser>();

		CCUser user = new CCUser();
		user.setUserId("axess1");
		user.setPassword("abc@123");
		user.setUserName("Peter Hanks");
		user.setCcName("Smart Bank Credit Card");
		user.setCcNumber(123456789);
		user.setTotalRewardsGained(0);
		user.setAvailableRedeemPoints(10000);
		users.add(user);
		
		user = new CCUser();
		user.setUserId("axess2");
		user.setPassword("abc@123");
		user.setUserName("Harry Simson");
		user.setCcName("Smart Bank Credit Card");
		user.setCcNumber(123456799);
		user.setTotalRewardsGained(0);
		user.setAvailableRedeemPoints(10000);
		users.add(user);
		
		user = new CCUser();
		user.setUserId("axess3");
		user.setPassword("abc@123");
		user.setUserName("Tom Willis");
		user.setCcName("Smart Bank Credit Card");
		user.setCcNumber(123456889);
		user.setTotalRewardsGained(0);
		user.setAvailableRedeemPoints(10000);
		users.add(user);

		return users;
	}

}
