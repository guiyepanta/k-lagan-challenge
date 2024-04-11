package com.klagan.challenge.adapter.in.web;

import java.time.LocalDateTime;

import org.apache.coyote.BadRequestException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klagan.challenge.application.port.in.web.PricePort;
import com.klagan.challenge.application.port.in.web.PriceRequest;
import com.klagan.challenge.common.TrackExecutionTime;
import com.klagan.challenge.common.WebAdapter;
import com.klagan.challenge.domain.Price;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@WebAdapter
@RestController
@RequestMapping("api/v1")
public class PriceController {

    private PricePort pricePort;

    public PriceController(PricePort pricePort) {
	this.pricePort = pricePort;
    }

    @TrackExecutionTime
    @GetMapping("/price/{applicationDate}/{productId}/{brandId}")
    public ResponseEntity<Price> getPriceBy(
	    @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate,
	    @PathVariable @Min(0) Integer productId,
	    @PathVariable @Min(0) Integer brandId) {

	Price response = pricePort.getPriceBy(applicationDate, productId, brandId);
	return new ResponseEntity<Price>(response, HttpStatus.OK);
    }

    @TrackExecutionTime
    @PostMapping(path = "/price")
    public ResponseEntity<Price> createPrice(@Valid @RequestBody PriceRequest request) {
	Price result = pricePort.create(request);
	return new ResponseEntity<Price>(result, HttpStatus.CREATED);
    }

    @TrackExecutionTime
    @PutMapping(path = "/price/{id}")
    public ResponseEntity<Price> updatePrice(@PathVariable("id") Integer priceId,
	    @Valid @RequestBody PriceRequest request) throws BadRequestException {
	Price response = pricePort.update(priceId, request);
	return new ResponseEntity<Price>(response, HttpStatus.OK);
    }
}
