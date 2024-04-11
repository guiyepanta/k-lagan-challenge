package com.klagan.challenge.adapter.out.persistence;

import java.time.LocalDateTime;

import com.klagan.challenge.adapter.out.persistence.entities.PriceEntity;
import com.klagan.challenge.adapter.out.persistence.mappers.PriceMapper;
import com.klagan.challenge.adapter.out.persistence.repositories.JPAPriceRepository;
import com.klagan.challenge.application.exceptions.PriceNotFoundException;
import com.klagan.challenge.application.port.out.db.CommandPricePort;
import com.klagan.challenge.application.port.out.db.QueryPricePort;
import com.klagan.challenge.common.PersistenceAdapter;
import com.klagan.challenge.domain.Price;

@PersistenceAdapter
public class PricePersistenceAdpater implements QueryPricePort, CommandPricePort {

    private JPAPriceRepository repository;

    public PricePersistenceAdpater(JPAPriceRepository priceRepository) {
	this.repository = priceRepository;
    }

    @Override
    public Price getPriceBy(LocalDateTime filterDate, Integer productId, Integer brandId) {
	return repository
		.findTopByEndDateGreaterThanEqualAndStartDateLessThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(filterDate, filterDate, productId, brandId)
		.map(PriceMapper::entityToDomain).orElseThrow(() -> new PriceNotFoundException("Price not found."));
    }

    @Override
    public Price save(Price price) {
	PriceEntity newPrice = PriceMapper.domainToEntity(price);
	return PriceMapper.entityToDomain(repository.save(newPrice));
    }
}
