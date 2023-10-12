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

import com.apidecrypto.dto.MarketDto;
import com.apidecrypto.dto.MarketRequestDto;
import com.apidecrypto.service.MarketService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/market")
public class MarketController {

	Logger LOGGER = LoggerFactory.getLogger(MarketController.class); 
	
	@Autowired
	private MarketService marketService;

	@GetMapping("/{id}")
	public ResponseEntity<MarketDto> findById(@PathVariable("id") long id) {
		MarketDto marketResponseDto = marketService.findById(id);
        return ResponseEntity.ok(marketResponseDto);
	}

	@GetMapping
	public ResponseEntity<List<MarketDto>> findAll() {
		List<MarketDto> marketList = marketService.findAll();
        return ResponseEntity.ok(marketList);
	}

	@PostMapping
	public ResponseEntity<MarketDto> save(@Valid @RequestBody MarketRequestDto marketRequestDto) {
		MarketDto marketResponseDto = marketService.save(marketRequestDto);
        return ResponseEntity.ok(marketResponseDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MarketDto> update(@PathVariable("id") long id, @Valid @RequestBody MarketRequestDto marketRequestDto) {
		MarketDto marketResponseDto = marketService.update(id, marketRequestDto);
        return ResponseEntity.ok(marketResponseDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Long> delete(@PathVariable Long id) {
		marketService.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
}
