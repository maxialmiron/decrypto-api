package com.apidecrypto.service;

import java.util.List;

import com.apidecrypto.dto.PrincipalDto;
import com.apidecrypto.dto.PrincipalRequestDto;
import com.apidecrypto.dto.StatsDto;

public interface PrincipalService {

	PrincipalDto findById(long id);
	
	List<PrincipalDto> findAll();
	
	PrincipalDto save(PrincipalRequestDto market);
	
	PrincipalDto update(long id, PrincipalRequestDto marketDto);
	
	void deleteById(long id);

	List<StatsDto> getStats();

}
