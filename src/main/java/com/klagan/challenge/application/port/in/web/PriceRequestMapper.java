package com.klagan.challenge.application.port.in.web;

import com.klagan.challenge.domain.Price;

public class PriceRequestMapper {

    public static Price requestToDomain(PriceRequest request) {
	Price result = Price.builder()
			    .brandId(request.getBrandId())
			    .startDate(request.getStartDate())
			    .endDate(request.getEndDate())
			    .priceList(request.getPriceList())
			    .productId(request.getProductId())
			    .priority(request.getPriority())
			    .price(request.getPrice())
			    .curr(request.getCurr())
			    .build();

	return result;
    }
}
