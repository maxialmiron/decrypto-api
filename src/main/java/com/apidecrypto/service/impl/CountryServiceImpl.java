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
	public CountryDto findById(long id) {
		Country country = countryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("No se encontró el Country con ID: " + id));
		
		LOGGER.info("country was found: {} ", country);
		return this.convertToDto(country);
	}

	@Override
	public List<Country> findAll() {
		LOGGER.info("searching country instances...");
		return countryRepository.findAll();
	}

	@Override
	public CountryDto save(CountryDto countryDto) {
		Country CountryEntity = countryRepository.save(this.convertToEntity(countryDto));
		return this.convertToDto(CountryEntity);
	}

	@Override
	public CountryDto update(long id, CountryDto countryDto) {

		Country Country = countryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("The Country with ID was not found:" + id));

		BeanUtils.copyProperties(countryDto, Country, "id");

		Country countryEntity = countryRepository.save(Country);

		return this.convertToDto(countryEntity);
	}

	@Override
	public void deleteById(long id) {
		Optional<Country> countryOptional = countryRepository.findById(id);
        if (countryOptional.isPresent()) {
            countryRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("The Country with ID was not found: " + id);
        }
	}

	private CountryDto convertToDto(Country Country) {
		CountryDto CountryResponseDto = modelMapper.map(Country, CountryDto.class);
		return CountryResponseDto;
	}

	private Country convertToEntity(CountryDto countryDto) {
		Country country = new Country();
		country.setCode(countryDto.getCode());
		country.setName(countryDto.getName());
		return country;
	}

}
