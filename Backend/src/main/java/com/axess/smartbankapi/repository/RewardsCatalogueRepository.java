package com.axess.smartbankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axess.smartbankapi.model.RewardsCatalogue;

@Repository
public interface RewardsCatalogueRepository extends JpaRepository<RewardsCatalogue, Long>{

}
