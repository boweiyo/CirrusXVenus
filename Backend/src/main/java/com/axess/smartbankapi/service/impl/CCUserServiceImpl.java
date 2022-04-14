package com.axess.smartbankapi.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.axess.smartbankapi.exception.RecordExistException;
import com.axess.smartbankapi.exception.RecordNotCreatedException;
import com.axess.smartbankapi.exception.RecordNotDeletedException;
import com.axess.smartbankapi.exception.RecordNotFoundException;
import com.axess.smartbankapi.exception.RecordNotUpdatedException;
import com.axess.smartbankapi.model.CCUser;
import com.axess.smartbankapi.repository.CCUserRepository;
import com.axess.smartbankapi.service.CCUserService;

@Service
public class CCUserServiceImpl implements CCUserService {

	@Autowired
	private CCUserRepository ccUserRepo;

	@Override
	public String saveUser(CCUser user) throws RecordExistException, RecordNotCreatedException {
		String message = "";
		try {
		this.ccUserRepo.save(user);
		return "Saved User - "+user.getUserName();
		}catch(DataAccessException e) {
			message = "Failed to save user - "+user.getUserName();
			throw new RecordNotCreatedException(message,e);
		}
		
	}

	@Override
	public List<CCUser> getAllUsers() throws RecordNotFoundException {
		try {

			return this.ccUserRepo.findAll();

		} catch (DataAccessException e) {
			throw new RecordNotFoundException("Something went wrong in fetching user list", e);
		}
	}

	@Override
	public CCUser getUserById(long id) throws RecordNotFoundException {
		return null;
	}

	@Override
	public String deleteUser(long id) throws RecordNotDeletedException {
		return null;
	}

	@Override
	public String updateUser(CCUser updatedUser)
			throws RecordNotUpdatedException, RecordExistException, RecordNotCreatedException {
		return null;
	}

	@Override
	public CCUser getLoginDetails(String userId, String password) throws RecordNotFoundException {

		try {
			Optional<CCUser> ccUser = this.ccUserRepo.findByUserIdAndPassword(userId, password);
			return ccUser.get();
		} catch (NoSuchElementException e) {
			throw new RecordNotFoundException("Invalid Credentials. Please check again.");
		}
	}

	@Override
	public String saveAllUsers(List<CCUser> users) throws RecordNotCreatedException {
		String message = "";
		try {
		this.ccUserRepo.saveAll(users);
		return "Saved Users - "+users.size();
		}catch(DataAccessException e) {
			message = "Failed to save users - "+0;
			throw new RecordNotCreatedException(message,e);
		}
	}

}
