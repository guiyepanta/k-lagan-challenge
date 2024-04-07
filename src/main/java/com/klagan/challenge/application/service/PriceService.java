package com.klagan.challenge.application.service;

import java.time.LocalDateTime;

import org.apache.coyote.BadRequestException;

import com.klagan.challenge.application.port.in.web.PricePort;
import com.klagan.challenge.application.port.in.web.PriceRequest;
import com.klagan.challenge.application.port.in.web.PriceRequestMapper;
import com.klagan.challenge.application.port.out.db.CommandPricePort;
import com.klagan.challenge.application.port.out.db.QueryPricePort;
import com.klagan.challenge.common.UseCase;
import com.klagan.challenge.domain.Price;

@UseCase
public class PriceService implements PricePort {

    private QueryPricePort queryPricePort;
    private CommandPricePort commandPricePort;

    public PriceService(QueryPricePort queryPricePort, CommandPricePort commandPricePort) {
	this.queryPricePort = queryPricePort;
	this.commandPricePort = commandPricePort;
    }

    @Override
    public Price getPriceBy(LocalDateTime applicationrDate, Integer productId, Integer brandId) {
	Price price = queryPricePort.getPriceBy(applicationrDate, productId, brandId);

	return price;
    }

    @Override
    public Price create(PriceRequest priceRequest) {
	Price price = PriceRequestMapper.requestToDomain(priceRequest);
	return commandPricePort.save(price);
    }

    @Override
    public Price update(Integer priceId, PriceRequest priceRequest) throws BadRequestException {
	Price price = PriceRequestMapper.requestToDomain(priceRequest);
	price.setId(priceId);

	return commandPricePort.save(price);
    }
}
