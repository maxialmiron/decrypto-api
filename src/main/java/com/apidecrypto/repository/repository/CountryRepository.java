package com.apidecrypto.repository.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apidecrypto.model.Country;
/**
 * 
 * @author Maximiliano Almiron
 *
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

	Optional<Country> findByCode(String code);
}
