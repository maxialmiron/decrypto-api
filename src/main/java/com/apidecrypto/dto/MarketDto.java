package com.apidecrypto.dto;

import jakarta.validation.constraints.NotNull;
/**
 * 
 * @author Maximiliano Almiron
 *
 */
public class MarketDto {
	
	private Integer id;
	
	@NotNull(message = "The code cannot be null")
    private String code;
    
	@NotNull(message = "The description cannot be null")
    private String description;
    
    @NotNull(message = "The name cannot be null")
    private CountryDto country;
    
    public MarketDto() {
    }
    
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public CountryDto getCountry() {
		return country;
	}
	
	public void setCountry(CountryDto country) {
		this.country = country;
	}

	@Override
    public String toString() {
        return "marketDto{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
