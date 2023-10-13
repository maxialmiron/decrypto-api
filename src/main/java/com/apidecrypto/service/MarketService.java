package com.apidecrypto.service;

import java.util.List;

import com.apidecrypto.dto.MarketDto;
import com.apidecrypto.dto.MarketRequestDto;

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
	List<MarketDto> findAll();
	
	/**
	 * 
	 * @param market
	 * @return
	 */
	MarketDto save(MarketRequestDto market);
	
	/**
	 * 
	 * @param id
	 * @param marketDto
	 * @return
	 */
	MarketDto update(long id, MarketRequestDto marketDto);
	
	/**
	 * 
	 * @param id
	 */
	void deleteById(long id);

}
