package com.axess.smartbankapi.service;

import java.util.List;

import com.axess.smartbankapi.dto.UserRedeemptionHistoryDto;
import com.axess.smartbankapi.exception.RecordExistException;
import com.axess.smartbankapi.exception.RecordNotCreatedException;
import com.axess.smartbankapi.exception.RecordNotFoundException;
import com.axess.smartbankapi.model.UserRedeemptionHistory;


public interface RedeemptionHistoryService {
	
	
	String saveHistory(UserRedeemptionHistoryDto history) throws RecordExistException, RecordNotCreatedException;
	List<UserRedeemptionHistory> getAll() throws RecordNotFoundException;
	UserRedeemptionHistory getByUser(long id) throws RecordNotFoundException;
	
	

}
