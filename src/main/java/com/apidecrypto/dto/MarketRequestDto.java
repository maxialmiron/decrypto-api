package com.apidecrypto.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Maximiliano Almiron
 *
 */
@JsonIgnoreProperties(value={ "id"})
public class MarketRequestDto extends MarketDto {
	
}
