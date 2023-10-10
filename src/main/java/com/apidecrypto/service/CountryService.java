package com.apidecrypto.service;

import java.util.List;

import com.apidecrypto.dto.CountryDto;
import com.apidecrypto.model.Country;

public interface CountryService {

	/**
	 * 
	 * @param id
	 * @return countryDto found
	 */
	CountryDto findById(long id);
	
	/**
	 * Retrieves all instances of Country.
	 *
	 * @return A list containing all instances of Country in the database.
	 * @throws DataAccessException If there is any issue accessing the database.
	 */
	List<Country> findAll();
	
	/**
	 * 
	 * @param countryDto
	 * @return countryDto saved
	 */
	CountryDto save(CountryDto countryDto);
	
	/**
	 * 
	 * @param id
	 * @param countryDto
	 * @return countryDto updated
	 */
	CountryDto update(long id, CountryDto countryDto);
	
	/**
	 * 
	 * @param id
	 */
	void deleteById(long id);

}
