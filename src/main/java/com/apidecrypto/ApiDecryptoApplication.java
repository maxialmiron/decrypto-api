package com.apidecrypto;

import static org.springframework.security.config.Customizer.withDefaults;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class ApiDecryptoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDecryptoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
	      .authenticated())
	      .httpBasic(withDefaults())
	      .formLogin(withDefaults())  
	      .csrf(AbstractHttpConfigurer::disable);
	    return http.build();
	}
}
