package com.axess.smartbankapi.service;

import java.util.List;

import com.axess.smartbankapi.exception.RecordNotCreatedException;
import com.axess.smartbankapi.exception.RecordNotFoundException;
import com.axess.smartbankapi.model.RewardsCatalogue;

public interface RewardCatalogueService {
	
	List<RewardsCatalogue> getAll() throws RecordNotFoundException;
	RewardsCatalogue getById(long id) throws RecordNotFoundException;
	
	String saveAllItems(List<RewardsCatalogue> items) throws RecordNotCreatedException;

}
