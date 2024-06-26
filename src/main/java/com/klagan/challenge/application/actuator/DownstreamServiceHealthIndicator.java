package com.klagan.challenge.application.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class DownstreamServiceHealthIndicator implements ReactiveHealthIndicator {

    @Override
    public Mono<Health> health() {
	return checkDownstreamServiceHealth().onErrorResume(ex -> Mono.just(new Health.Builder().down(ex).build()));
    }

    private Mono<Health> checkDownstreamServiceHealth() {
	return Mono.just(new Health.Builder().up().build());
    }
}