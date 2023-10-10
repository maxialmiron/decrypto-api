package com.apidecrypto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
/**
 * 
 * @author Maximiliano Almiron
 *
 */
@EqualsAndHashCode
@Entity
public class Country {

	@Id
    @NotNull(message = "The code cannot be null")
    @Column(name = "code", unique = true)
	private String code;
	
    @Column(name = "name", unique = true)
    @NotNull(message = "The name cannot be null")
	private String name;

    public Country() {
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
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
	
}
