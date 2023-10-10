package com.apidecrypto.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apidecrypto.dto.MarketDto;
import com.apidecrypto.dto.PrincipalDto;
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
		Principal principal = principalRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" + id));
		return this.convertToDto(principal);
	}
	
	@Override
	public List<PrincipalDto> findAll() {
		List<PrincipalDto> principalDtoList = principalRepository.findAll().stream().map(p -> this.convertToDto(p)).collect(Collectors.toList());
		return principalDtoList;}
	
	@Override
	public PrincipalDto save(PrincipalDto principalDto) {
		Principal principal = principalRepository.save(this.convertToEntity(principalDto));
		LOGGER.info("Entity saved: {}", principal);
		return this.convertToDto(principal);
	}

	@Override
	public PrincipalDto update(long id, PrincipalDto marketDto) {

		Principal market = principalRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" + id));

		BeanUtils.copyProperties(marketDto, market, "id");

		Principal principalEntity = principalRepository.save(market);

		return this.convertToDto(principalEntity);
	}

	@Override
	public void deleteById(long id) {
		principalRepository.deleteById(id);
	}

	private PrincipalDto convertToDto(Principal principal) {
		PrincipalDto principalDto = modelMapper.map(principal, PrincipalDto.class);
		
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
		principal.setMarket(markets);
		
		return principal;
	}

}
