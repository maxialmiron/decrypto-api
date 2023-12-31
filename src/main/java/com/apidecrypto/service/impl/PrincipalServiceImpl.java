package com.apidecrypto.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apidecrypto.dto.MarketDto;
import com.apidecrypto.dto.MarketProjection;
import com.apidecrypto.dto.MarketStatsDto;
import com.apidecrypto.dto.PrincipalDto;
import com.apidecrypto.dto.PrincipalRequestDto;
import com.apidecrypto.dto.StatsDto;
import com.apidecrypto.model.Market;
import com.apidecrypto.model.Principal;
import com.apidecrypto.repository.repository.MarketRepository;
import com.apidecrypto.repository.repository.PrincipalRepository;
import com.apidecrypto.service.PrincipalService;

import jakarta.persistence.EntityNotFoundException;

/**
 * 
 * @author Maximiliano Almiron
 *
 */
@Service
@Transactional
public class PrincipalServiceImpl implements PrincipalService {

	Logger LOGGER = LoggerFactory.getLogger(PrincipalServiceImpl.class);

	@Autowired
	private PrincipalRepository principalRepository;

	@Autowired
	private MarketRepository marketRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PrincipalDto findById(long id) {
		LOGGER.info("finding by ID:" + id);
		Principal principal = principalRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" + id));
		return this.convertToDto(principal);
	}
	
	@Override
	public List<PrincipalDto> findAll() {
		LOGGER.info("finding all principal instances");
		List<PrincipalDto> principalDtoList = principalRepository.findAll().stream().map(p -> this.convertToDto(p)).collect(Collectors.toList());
		return principalDtoList;}
	
	@Override
	public PrincipalDto save(PrincipalRequestDto principalRequestDto) {
		Principal principal = principalRepository.save(this.convertToEntity(principalRequestDto));
		LOGGER.info("Entity saved: {}", principal);
		return this.convertToDto(principal);
	}

	@Override
	public PrincipalDto update(long id, PrincipalRequestDto principalRequestDto) {

		Principal principal = principalRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" + id));

		BeanUtils.copyProperties(principalRequestDto, principal, "id");

		Principal principalEntity = principalRepository.save(principal);

		LOGGER.info("Entity update: {}", principalEntity);

		return this.convertToDto(principalEntity);
	}

	@Override
	public void deleteById(long id) {
		principalRepository.deleteById(id);
	}

	private PrincipalDto convertToDto(Principal principal) {
		PrincipalDto principalDto = modelMapper.map(principal, PrincipalRequestDto.class);
		
		List<MarketDto> list = principal.getMarket().stream().map(p -> this.convertToDto(p)).collect(Collectors.toList());

		principalDto.setMarkets(list);
		
		return principalDto;
	}
	
	private MarketDto convertToDto(Market market) {
		MarketDto marketDto = modelMapper.map(market, MarketDto.class);
		return marketDto;
	}

	private Principal convertToEntity(PrincipalDto principalDto) {
		Principal principal = new Principal();
		principal.setDescription(principalDto.getDescription());

		List<Market> markets = marketRepository.findByIdIn(principalDto.getMarkets().stream().map(p -> p.getId())
		        .collect(Collectors.toList()));
		
		if(principalDto.getMarkets().size() != markets.size()) {
			LOGGER.error("market was not found");
			throw new EntityNotFoundException("market was not found");
		}
		
		principal.setMarket(markets);
		
		return principal;
	}
	
	@Override
	public List<StatsDto> getStats() {
		
		LOGGER.info("Getting Stats:");
		
		List<MarketProjection> principalList = principalRepository.countPrincipalsByCountryAndMarket();
		
		int totalPrincipals = principalList.stream()
				  .reduce(0, (ac, user) -> ac + user.getCount(), Integer::sum);
		
		Map<String, List<MarketProjection>> principalsPercentageByCountryAndMarket = principalList.stream()
				.collect(Collectors.groupingBy(MarketProjection::getCountry));	
		
		List<StatsDto> statsDtoList = new ArrayList<StatsDto>();
		
		mapPercentages(totalPrincipals, principalsPercentageByCountryAndMarket, statsDtoList);
		
		return statsDtoList;
	}

	/**
	 * 
	 *  Maps all principals by country, market and its amount
	 * 
	 * @param totalPrincipals
	 * @param principalsPercentageByCountryAndMarket
	 * @param statsDtoList
	 */
	private void mapPercentages(int totalPrincipals,
								Map<String, List<MarketProjection>> principalsPercentageByCountryAndMarket, 
								List<StatsDto> statsDtoList) {
		
		principalsPercentageByCountryAndMarket.forEach((name, list) -> {
			StatsDto statsDto = new StatsDto();
			statsDto.setCountry(name);
			statsDtoList.add(statsDto);
			
			List<MarketStatsDto> marketStatsDtoList = new ArrayList<MarketStatsDto>();
		
			for (MarketProjection marketProjection : list) {
				MarketStatsDto marketStatsDto = new MarketStatsDto();
				marketStatsDto.setName(marketProjection.getMarketCode());
		
				float percentage = (float) (marketProjection.getCount() * 100) / totalPrincipals;
				BigDecimal bd = new BigDecimal(percentage);
				marketStatsDto.setPercentage(bd.setScale(2, RoundingMode.HALF_EVEN).toString());
				
				marketStatsDtoList.add(marketStatsDto);
			}
		
			statsDto.setMarket(marketStatsDtoList);
		});
	}

}
