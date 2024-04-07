package com.klagan.challenge.adapter.out.persistence;

import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.klagan.challenge.adapter.out.persistence.entities.PriceEntity;
import com.klagan.challenge.adapter.out.persistence.repositories.JPAPriceRepository;
import com.klagan.challenge.application.exceptions.PriceNotFoundException;
import com.klagan.challenge.domain.Price;
import com.klagan.challenge.utils.DateUtil;

@ExtendWith(MockitoExtension.class)
class PricePersistenceAdapterTest {

    @Mock
    JPAPriceRepository repository;

    @InjectMocks
    PricePersistenceAdpater adapter;

    @Test
    @DisplayName("Test 01: get price OK (200)")
    public void test01() throws ParseException {

	// GIVEN
	PriceEntity priceMock = new PriceEntity();
	priceMock.setId(888);
	priceMock.setBrandId(1);
	priceMock.setStartDate(DateUtil.getDateFromString("2020-06-14 00:00:00"));
	priceMock.setEndDate(DateUtil.getDateFromString("2020-12-31 23:59:59"));
	priceMock.setPriceList(1);
	priceMock.setProductId(35455);
	priceMock.setPriority(0);
	priceMock.setPrice(Float.valueOf("35.50"));
	priceMock.setCurr("EUR");

	Optional<PriceEntity> optionalPriceMock = Optional.of(priceMock);

	// WHEN
	LocalDateTime applicationDate = DateUtil.getDateFromString("2020-06-14 10:00:00");
	Integer productId = 35455;
	Integer brandId = 1;

	when(repository
		.findTopByEndDateGreaterThanEqualAndStartDateLessThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
			applicationDate, applicationDate, productId, brandId))
		.thenReturn(optionalPriceMock);

	Price result = adapter.getPriceBy(applicationDate, productId, brandId);

	// THEN
	Assertions.assertEquals(888, result.getId());
    }

    @Test
    @DisplayName("Test 02: Price not found exception")
    public void test02() throws ParseException {

	// GIVEN
	Optional<PriceEntity> optionalPriceMock = Optional.empty();

	// WHEN
	LocalDateTime applicationDate = DateUtil.getDateFromString("2020-06-14 10:00:00");
	Integer productId = 35455;
	Integer brandId = 1;

	when(repository
		.findTopByEndDateGreaterThanEqualAndStartDateLessThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
			applicationDate, applicationDate, productId, brandId))
		.thenReturn(optionalPriceMock);

	// THEN
	PriceNotFoundException exception = Assertions.assertThrows(PriceNotFoundException.class, () -> {
	    adapter.getPriceBy(applicationDate, productId, brandId);
	});

	Assertions.assertEquals("Price not found.", exception.getMessage());

    }
}
