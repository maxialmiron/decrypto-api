package com.apidecrypto.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apidecrypto.dto.MarketDto;
import com.apidecrypto.model.Country;
import com.apidecrypto.model.Market;
import com.apidecrypto.repository.repository.CountryRepository;
import com.apidecrypto.repository.repository.MarketRepository;
import com.apidecrypto.service.MarketService;
import com.decrypto.exception.NestedEntityNotFoundException;

import jakarta.persistence.EntityNotFoundException;


/**
 * 
 * @author Maximiliano Almiron
 *
 */
@Service
@Transactional
public class MarketServiceImpl implements MarketService {

	Logger LOGGER = LoggerFactory.getLogger(MarketServiceImpl.class);

	@Autowired
	private MarketRepository marketRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public MarketDto findById(long id) {
		Market market = marketRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" + id));
		LOGGER.debug("Market found: {} ", market);
		return this.convertToDto(market);
	}
	
	@Override
	public List<Market> findAll() {
		LOGGER.debug("Finding all country instances");
		return marketRepository.findAll();
	}
	
	@Override
	public MarketDto save(MarketDto marketDto) {
		Market marketEntity = marketRepository.save(this.convertToEntity(marketDto));
		LOGGER.debug("the market was saved: {}", marketEntity);
		return this.convertToDto(marketEntity);
	}

	@Override
	public MarketDto update(long id, MarketDto marketDto) {

		Market market = marketRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" + id));

		Country country = countryRepository.findByCode(marketDto.getCountry().getCode())
				.orElseThrow(() -> new NestedEntityNotFoundException(marketDto.getCountry().getCode()));

		BeanUtils.copyProperties(marketDto, market, "id");

		market.setCountry(country);

		LOGGER.debug("Updating market: {}", market);
		Market marketEntity = marketRepository.save(market);
		
		return this.convertToDto(marketEntity);
	}

	@Override
	public void deleteById(long id) {
		LOGGER.debug("Deleting market with ID: ", id);
		marketRepository.deleteById(id);
	}

	private MarketDto convertToDto(Market market) {
		MarketDto marketResponseDto = modelMapper.map(market, MarketDto.class);
		return marketResponseDto;
	}

	private Market convertToEntity(MarketDto marketDto) {
		Market market = new Market();
		market.setCode(marketDto.getCode());
		market.setDescription(marketDto.getDescription());

		Country country = countryRepository.findByCode(marketDto.getCountry().getCode()).orElseThrow(
				() -> new NestedEntityNotFoundException(marketDto.getCountry().getCode()));
		
		market.setCountry(country);

		return market;
	}

}
