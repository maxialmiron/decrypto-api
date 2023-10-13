package com.apidecrypto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apidecrypto.dto.StatsDto;
import com.apidecrypto.service.PrincipalService;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

	@Autowired
	private PrincipalService principalService;
	
	@GetMapping
	public ResponseEntity<List<StatsDto>> stats() {
		List<StatsDto> statsDtoList = principalService.getStats();
        return ResponseEntity.ok(statsDtoList);
	}
}
