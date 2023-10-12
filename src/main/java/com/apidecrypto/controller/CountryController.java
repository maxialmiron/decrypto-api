package com.apidecrypto.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.apidecrypto.model.Country;
import com.apidecrypto.service.CountryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/country")
public class CountryController {

	Logger LOGGER = LoggerFactory.getLogger(CountryController.class); 
	
	@Autowired
	private CountryService countryService;

	@GetMapping("/{code}")
	public ResponseEntity<CountryDto> findByCode(@PathVariable("code") String code) {
		CountryDto countryDto = countryService.findByCode(code);
        return ResponseEntity.ok(countryDto);
	}

	@GetMapping
	public ResponseEntity<List<Country>> findAll() {
		List<Country> countryList = countryService.findAll();
		return new ResponseEntity<>(countryList, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CountryDto> save(@Valid @RequestBody CountryDto countryRequestDto) {
		CountryDto countryResponseDto = countryService.save(countryRequestDto);
		return ResponseEntity.ok(countryResponseDto);
	}
	
	@PutMapping("/{code}")
	public ResponseEntity<CountryDto> update(@PathVariable("code") String code, @Valid @RequestBody CountryDto countryRequestDto) {
		CountryDto countryResponseDto = countryService.update(code, countryRequestDto);
        return ResponseEntity.ok(countryResponseDto);
	}
	
	@DeleteMapping(value = "/{code}")
	public ResponseEntity<?> delete(@PathVariable String code) {
		countryService.deleteByCode(code);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
}
