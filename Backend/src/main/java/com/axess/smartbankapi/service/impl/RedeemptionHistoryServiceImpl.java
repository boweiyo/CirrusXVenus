package com.axess.smartbankapi.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axess.smartbankapi.dto.UserRedeemptionHistoryDto;
import com.axess.smartbankapi.exception.RecordExistException;
import com.axess.smartbankapi.exception.RecordNotCreatedException;
import com.axess.smartbankapi.exception.RecordNotFoundException;
import com.axess.smartbankapi.model.CCUser;
import com.axess.smartbankapi.model.UserRedeemptionHistory;
import com.axess.smartbankapi.repository.CCUserRepository;
import com.axess.smartbankapi.repository.RedeemptionHistoryRepository;
import com.axess.smartbankapi.service.RedeemptionHistoryService;

@Service
public class RedeemptionHistoryServiceImpl implements RedeemptionHistoryService {

	@Autowired
	private RedeemptionHistoryRepository historyRepo;
	
	@Autowired
	private CCUserRepository ccUserRepo;
	
	
	@Override
	public String saveHistory(UserRedeemptionHistoryDto historyDto) throws RecordExistException, RecordNotCreatedException {
		String message ="Item saved in history";
		CCUser user = ccUserRepo.findById(historyDto.getCcNumber()).get();
		user.setAvailableRedeemPoints(user.getAvailableRedeemPoints() - historyDto.getTotalPointsRedeemed());
		user.setTotalRewardsGained(historyDto.getTotalAmountGained());
		ccUserRepo.save(user);
		
		historyDto.getItemsRedeemed().forEach(item ->{
			
			UserRedeemptionHistory historyData = new UserRedeemptionHistory();
			historyData.setCatalogue(item);
			historyData.setOrderdate(LocalDate.now());
			historyData.setQuantity(historyDto.getQuantity());
			historyData.setCcUser(user);
			
			historyRepo.save(historyData);
			
			
			
		});
		
		return message;
	}

	@Override
	public List<UserRedeemptionHistory> getAll() throws RecordNotFoundException {
		return historyRepo.findAll();
	}

	@Override
	public UserRedeemptionHistory getByUser(long id) throws RecordNotFoundException {
		return null;
	}

}
