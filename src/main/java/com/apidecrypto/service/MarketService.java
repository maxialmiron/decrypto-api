package com.apidecrypto.service;

import java.util.List;

import com.apidecrypto.dto.MarketDto;
import com.apidecrypto.model.Market;

public interface MarketService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	MarketDto findById(long id);
	
	/**
	 * 
	 * @return
	 */
	List<Market>findAll();
	
	/**
	 * 
	 * @param market
	 * @return
	 */
	MarketDto save(MarketDto market);
	
	/**
	 * 
	 * @param id
	 * @param marketDto
	 * @return
	 */
	MarketDto update(long id, MarketDto marketDto);
	
	/**
	 * 
	 * @param id
	 */
	void deleteById(long id);

}
