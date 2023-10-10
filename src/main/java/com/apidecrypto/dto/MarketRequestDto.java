package com.apidecrypto.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author Maximiliano Almiron
 *
 */
public class MarketRequestDto extends MarketDto {
	@JsonIgnore
	private Integer id;
}
