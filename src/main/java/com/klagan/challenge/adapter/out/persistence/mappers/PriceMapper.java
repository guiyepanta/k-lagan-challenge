package com.klagan.challenge.adapter.out.persistence.mappers;

import com.klagan.challenge.adapter.out.persistence.entities.PriceEntity;
import com.klagan.challenge.domain.Price;

public class PriceMapper {

    public static Price entityToDomain(PriceEntity entity) {
	Price result = Price.builder().id(entity.getId()).brandId(entity.getBrandId()).startDate(entity.getStartDate())
		.endDate(entity.getEndDate()).priceList(entity.getPriceList()).productId(entity.getProductId())
		.priority(entity.getPriority()).price(entity.getPrice()).curr(entity.getCurr()).build();

	return result;
    }

    public static PriceEntity domainToEntity(Price domain) {

	PriceEntity result = PriceEntity.builder().id(domain.getId()).brandId(domain.getBrandId())
		.startDate(domain.getStartDate()).endDate(domain.getEndDate()).priceList(domain.getPriceList())
		.productId(domain.getProductId()).priority(domain.getPriority()).price(domain.getPrice())
		.curr(domain.getCurr()).build();

	return result;
    }
}
