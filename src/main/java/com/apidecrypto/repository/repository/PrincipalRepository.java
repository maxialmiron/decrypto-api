package com.apidecrypto.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apidecrypto.model.Principal;
/**
 * 
 * @author Maximiliano Almiron
 *
 */
public interface PrincipalRepository extends JpaRepository<Principal, Long> {

}
