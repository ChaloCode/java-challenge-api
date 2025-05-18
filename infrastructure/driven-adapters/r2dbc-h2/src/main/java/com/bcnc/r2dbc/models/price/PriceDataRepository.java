package com.bcnc.r2dbc.models.price;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PriceDataRepository extends ReactiveCrudRepository<PriceData, Long>,
    ReactiveQueryByExampleExecutor<PriceData> {
  Mono<PriceData> findAllByBrandIdAndProductIdAndPriceListAndPriority(Long brandId, Long productId, Integer priceList, Integer priority);
}
