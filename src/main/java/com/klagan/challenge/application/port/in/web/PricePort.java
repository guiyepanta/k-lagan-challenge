package com.klagan.challenge.application.port.in.web;

import java.time.LocalDateTime;

import com.klagan.challenge.domain.Price;

public interface PricePort {
    // Query methods
    Price getPriceBy(LocalDateTime applicationrDate, Integer productId, Integer brandId);
}
