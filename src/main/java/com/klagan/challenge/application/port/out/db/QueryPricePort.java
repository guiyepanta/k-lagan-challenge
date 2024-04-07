package com.klagan.challenge.application.port.out.db;

import java.time.LocalDateTime;

import com.klagan.challenge.domain.Price;

public interface QueryPricePort {

    Price getPriceBy(LocalDateTime filterDate, Integer productId, Integer brandId);

}