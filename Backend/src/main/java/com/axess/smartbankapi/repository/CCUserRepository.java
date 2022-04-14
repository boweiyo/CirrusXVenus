package com.axess.smartbankapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axess.smartbankapi.model.CCUser;

@Repository
public interface CCUserRepository extends JpaRepository<CCUser, Long>{
	
	Optional<CCUser> findByUserIdAndPassword(String userId, String password);

}
