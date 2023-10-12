package com.apidecrypto.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apidecrypto.dto.CountryDto;
import com.apidecrypto.model.Country;
import com.apidecrypto.repository.repository.CountryRepository;
import com.apidecrypto.service.CountryService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

/**
 * 
 * @author Maximiliano Almiron
 *
 */
@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CountryDto findByCode(String code) {
		Country country = countryRepository.findByCode(code)
				.orElseThrow(() -> new EntityNotFoundException("" + code));
		
		LOGGER.info("country found: {} ", country);
		return this.convertToDto(country);
	}

	@Override
	public List<Country> findAll() {
		LOGGER.info("searching country instances...");
		return countryRepository.findAll();
	}

	@Override
	public CountryDto save(CountryDto countryDto) {
		if(countryRepository.findByCode(countryDto.getCode()).isPresent()) {
			throw new EntityExistsException(countryDto.getCode());
		}
		
		Country countryEntity = countryRepository.save(this.convertToEntity(countryDto));
		LOGGER.info("country saved");
		return this.convertToDto(countryEntity);
	}

	@Override
	public CountryDto update(String code, CountryDto countryDto) {

		countryRepository.findByCode(code)
				.orElseThrow(() -> new EntityNotFoundException("" + code));

		Country countryEntity = countryRepository.save(this.convertToEntity(countryDto));
		LOGGER.info("country updated");
		return this.convertToDto(countryEntity);
	}

	@Override
	public void deleteByCode(String code) {
		Optional<Country> countryOptional = countryRepository.findByCode(code);
        if (countryOptional.isPresent()) {
            countryRepository.delete(countryOptional.get());
    		LOGGER.info("country deleted");
        } else {
            throw new EntityNotFoundException("" + code);
        }
	}

	private CountryDto convertToDto(Country Country) {
		CountryDto countryResponseDto = modelMapper.map(Country, CountryDto.class);
		return countryResponseDto;
	}

	private Country convertToEntity(CountryDto countryDto) {
		Country country = new Country();
		BeanUtils.copyProperties(countryDto, country);
		return country;
	}

}
