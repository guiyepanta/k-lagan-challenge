package com.klagan.challenge.application.service;

import java.time.LocalDateTime;

import com.klagan.challenge.application.port.in.web.PricePort;
import com.klagan.challenge.application.port.out.db.QueryPricePort;
import com.klagan.challenge.common.UseCase;
import com.klagan.challenge.domain.Price;

@UseCase
public class PriceService implements PricePort {

    private QueryPricePort queryPricePort;

    public PriceService(QueryPricePort queryPricePort) {
	this.queryPricePort = queryPricePort;
    }

    @Override
    public Price getPriceBy(LocalDateTime applicationrDate, Integer productId, Integer brandId) {
	Price price = queryPricePort.getPriceBy(applicationrDate, productId, brandId);

	return price;
    }
}
