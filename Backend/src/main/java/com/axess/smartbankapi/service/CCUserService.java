package com.axess.smartbankapi.service;

import java.util.List;

import com.axess.smartbankapi.exception.RecordExistException;
import com.axess.smartbankapi.exception.RecordNotCreatedException;
import com.axess.smartbankapi.exception.RecordNotDeletedException;
import com.axess.smartbankapi.exception.RecordNotFoundException;
import com.axess.smartbankapi.exception.RecordNotUpdatedException;
import com.axess.smartbankapi.model.CCUser;


public interface CCUserService {
	
	CCUser getLoginDetails(String userId, String password) throws RecordNotFoundException;
	String saveUser(CCUser user) throws RecordExistException, RecordNotCreatedException;
	List<CCUser> getAllUsers() throws RecordNotFoundException;
	CCUser getUserById(long id) throws RecordNotFoundException;
	String deleteUser(long id) throws RecordNotDeletedException;
	String updateUser(CCUser updatedUser) throws RecordNotUpdatedException, RecordExistException, RecordNotCreatedException;
	
	String saveAllUsers(List<CCUser> users) throws RecordNotCreatedException;

}
