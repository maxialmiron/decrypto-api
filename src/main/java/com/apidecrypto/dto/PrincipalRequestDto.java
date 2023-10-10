package com.apidecrypto.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author Maximiliano Almiron
 *
 */
public class PrincipalRequestDto extends PrincipalDto {
	@JsonIgnore
	private Integer id;
}
