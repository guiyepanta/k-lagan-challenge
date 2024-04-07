package com.klagan.challenge.adapter.out.persistence.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klagan.challenge.adapter.out.persistence.entities.PriceEntity;

@Repository
public interface JPAPriceRepository extends JpaRepository<PriceEntity, Integer> {

    Optional<PriceEntity> findTopByEndDateGreaterThanEqualAndStartDateLessThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
	    LocalDateTime starDate, LocalDateTime endDate, Integer productId, Integer brandId);
}
