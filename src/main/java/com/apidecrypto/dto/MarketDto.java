package com.apidecrypto.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @JsonIgnoreProperties("name")
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
    public String toString() {
        return "marketDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
