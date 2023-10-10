package com.apidecrypto.repository.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apidecrypto.dto.MarketProjection;
import com.apidecrypto.model.Principal;
/**
 * 
 * @author Maximiliano Almiron
 *
 */
public interface PrincipalRepository extends JpaRepository<Principal, Long> {

	  @Query(value ="SELECT COUNT(p.id) as count, c.name as country, m.code as marketCode FROM PRINCIPAL_MARKET pm " +
	           "INNER JOIN PRINCIPAL p ON p.id = pm.principal_id " +
	           "INNER JOIN MARKET m ON m.id = pm.market_id " +
	           "INNER JOIN COUNTRY c ON c.code = m.country " +
	           "GROUP BY c.code, m.code ", nativeQuery = true)
	  List<MarketProjection> countPrincipalsByCountryAndMarket();
	  
}
