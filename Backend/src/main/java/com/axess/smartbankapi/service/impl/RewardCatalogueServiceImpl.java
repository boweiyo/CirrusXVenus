package com.axess.smartbankapi.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.axess.smartbankapi.exception.RecordNotCreatedException;
import com.axess.smartbankapi.exception.RecordNotFoundException;
import com.axess.smartbankapi.model.RewardsCatalogue;
import com.axess.smartbankapi.repository.RewardsCatalogueRepository;
import com.axess.smartbankapi.service.RewardCatalogueService;

@Service
public class RewardCatalogueServiceImpl implements RewardCatalogueService {

	@Autowired
	private RewardsCatalogueRepository rcRepo;

	@Override
	public List<RewardsCatalogue> getAll() throws RecordNotFoundException {
		return this.rcRepo.findAll();
	}

	@Override
	public RewardsCatalogue getById(long id) throws RecordNotFoundException {

		try {
			Optional<RewardsCatalogue> rc = this.rcRepo.findById(id);
			return rc.get();
		} catch (NoSuchElementException e) {
			throw new RecordNotFoundException(e);
		}

	}

	@Override
	public String saveAllItems(List<RewardsCatalogue> items) throws RecordNotCreatedException {
		String message = "";
		try {
		this.rcRepo.saveAll(items);
		return "Saved items - "+items.size();
		}catch(DataAccessException e) {
			message = "Failed to save items - "+0;
			throw new RecordNotCreatedException(message,e);
		}
	}

}
