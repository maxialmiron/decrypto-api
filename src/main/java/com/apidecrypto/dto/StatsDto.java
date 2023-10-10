package com.apidecrypto.dto;

import java.util.List;

public class StatsDto {
	
	String country;
	
	List<MarketStatsDto> market;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<MarketStatsDto> getMarket() {
		return market;
	}

	public void setMarket(List<MarketStatsDto> market) {
		this.market = market;
	}

}
