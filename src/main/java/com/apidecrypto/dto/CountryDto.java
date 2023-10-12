package com.apidecrypto.dto;


import jakarta.validation.constraints.NotNull;
/**
 * 
 * @author Maximiliano Almiron
 *
 */
public class CountryDto {
	
	private String code;
	
    @NotNull(message = "The name cannot be nul")
	private String name;

    public CountryDto() {
    }
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public String toString() {
        return "countryDto{" +
                "code=" + this.code +
                ", nombre='" + this.name + '\'' +
                '}';
    }

}
