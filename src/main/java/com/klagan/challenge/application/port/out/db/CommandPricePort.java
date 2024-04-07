package com.klagan.challenge.application.port.out.db;

import com.klagan.challenge.domain.Price;

public interface CommandPricePort {

    Price save(Price price);

}