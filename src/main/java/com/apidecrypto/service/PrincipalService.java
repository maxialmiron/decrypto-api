package com.apidecrypto.service;

import java.util.List;

import com.apidecrypto.dto.PrincipalDto;
import com.apidecrypto.model.Principal;

public interface PrincipalService {

	PrincipalDto findById(long id);
	
	List<PrincipalDto> findAll();
	
	PrincipalDto save(PrincipalDto market);
	
	PrincipalDto update(long id, PrincipalDto marketDto);
	
	void deleteById(long id);

}
