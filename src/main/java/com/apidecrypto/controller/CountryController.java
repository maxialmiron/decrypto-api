package com.apidecrypto.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apidecrypto.dto.CountryDto;
import com.apidecrypto.dto.CountryRequestDto;
import com.apidecrypto.model.Country;
import com.apidecrypto.service.CountryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/country")
public class CountryController {

	Logger LOGGER = LoggerFactory.getLogger(CountryController.class); 
	
	@Autowired
	private CountryService countryService;

	@GetMapping("/{id}")
	public ResponseEntity<CountryDto> findById(@PathVariable("id") long id) {
		CountryDto countryDto = countryService.findById(id);
        return ResponseEntity.ok(countryDto);
	}

	@GetMapping
	public ResponseEntity<List<Country>> findAll() {
		List<Country> countryList = countryService.findAll();
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CountryDto> save(@Valid @RequestBody CountryRequestDto countryRequestDto) {
		CountryDto countryResponseDto = countryService.save(countryRequestDto);
		return ResponseEntity.ok(countryResponseDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CountryDto> update(@PathVariable("id") long id, @Valid @RequestBody CountryRequestDto countryRequestDto) {
		CountryDto countryResponseDto = countryService.update(id, countryRequestDto);
        return ResponseEntity.ok(countryResponseDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Long> delete(@PathVariable Long id) {
		countryService.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
}
