package com.bcnc.usecase.repository;

import com.bcnc.model.price.Price;
import reactor.core.publisher.Mono;

public interface PriceRepository {
  Mono<Price> saveOrUpdate(Price price);
}
