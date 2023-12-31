package com.apidecrypto.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;

/**
 * 
 * @author Maximiliano Almiron
 *
 */
public class PrincipalDto {
	
    private Integer id;
	
    @NotNull(message = "The description cannot be null")
    private String description;
    
    @NotNull(message = "The markets cannot be null")
    private List<MarketDto> markets = new ArrayList<MarketDto>();
    
    public PrincipalDto() {
    }
    
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public List<MarketDto> getMarkets() {
		return markets;
	}

	public void setMarkets(List<MarketDto> markets) {
		this.markets = markets;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
