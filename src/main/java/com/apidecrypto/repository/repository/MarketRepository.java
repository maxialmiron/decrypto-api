package com.apidecrypto.repository.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apidecrypto.model.Market;
/**
 * 
 * @author Maximiliano Almiron
 *
 */
public interface MarketRepository extends JpaRepository<Market, Long> {
	
	boolean deleteMarketById(Long id);
	
    List<Market> findByIdIn(List<Integer> ids);

}