package com.klagan.challenge.adapter.in.web;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klagan.challenge.application.port.in.web.PricePort;
import com.klagan.challenge.common.WebAdapter;
import com.klagan.challenge.domain.Price;

import jakarta.validation.constraints.Min;

@WebAdapter
@RestController
@RequestMapping("api/v1")
public class PriceController {

    private PricePort pricePort;

    public PriceController(PricePort pricePort) {
	this.pricePort = pricePort;
    }

    @GetMapping("/price/{applicationDate}/{productId}/{brandId}")
    public ResponseEntity<Price> getPriceBy(
	    @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate,
	    @PathVariable @Min(0) Integer productId, @PathVariable @Min(0) Integer brandId) {

	Price response = pricePort.getPriceBy(applicationDate, productId, brandId);
	return new ResponseEntity<Price>(response, HttpStatus.OK);
    }
}
