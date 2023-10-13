package com.apidecrypto.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author Maximiliano Almiron
 *
 */
@JsonIgnoreProperties({"id"})
public class PrincipalRequestDto extends PrincipalDto {
	
}
