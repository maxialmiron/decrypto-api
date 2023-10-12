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

import com.apidecrypto.dto.PrincipalDto;
import com.apidecrypto.dto.PrincipalRequestDto;
import com.apidecrypto.service.PrincipalService;

import jakarta.validation.Valid;

/**
 * 
 * @author Maximiliano Almiron
 *
 */
@RestController
@RequestMapping("/api/principal")
public class PrincipalController {

	Logger LOGGER = LoggerFactory.getLogger(PrincipalController.class); 
	
	@Autowired
	private PrincipalService principalService;

	@GetMapping("/{id}")
	public ResponseEntity<PrincipalDto> findById(@PathVariable("id") Long id) {
		if(id == null) {
		    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		PrincipalDto principalResponseDto = principalService.findById(id);
        return ResponseEntity.ok(principalResponseDto);
	}

	@GetMapping
	public ResponseEntity<List<PrincipalDto>> findAll() {
		List<PrincipalDto> principalList = principalService.findAll();
        return ResponseEntity.ok(principalList);
	}

	@PostMapping
	public ResponseEntity<PrincipalDto> save(@Valid @RequestBody PrincipalRequestDto PrincipalDto) {
		PrincipalDto principalResponseDto = principalService.save(PrincipalDto);
        return ResponseEntity.ok(principalResponseDto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PrincipalDto> update(@PathVariable("id") long id, @Valid @RequestBody PrincipalRequestDto PrincipalDto) {
		PrincipalDto principalResponseDto = principalService.update(id, PrincipalDto);
        return ResponseEntity.ok(principalResponseDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Long> delete(@PathVariable Long id) {
		principalService.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
