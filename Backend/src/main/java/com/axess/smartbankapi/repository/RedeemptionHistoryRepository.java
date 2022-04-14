package com.axess.smartbankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axess.smartbankapi.model.UserRedeemptionHistory;

@Repository
public interface RedeemptionHistoryRepository extends JpaRepository<UserRedeemptionHistory, Long> {

}
