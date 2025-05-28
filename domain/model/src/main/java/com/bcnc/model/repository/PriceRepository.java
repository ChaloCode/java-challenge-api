package com.bcnc.model.repository;

import com.bcnc.model.price.entities.Price;
import com.bcnc.model.price.value.objects.PriceParam;
import reactor.core.publisher.Mono;

public interface PriceRepository {
  Mono<Price> getPrices(PriceParam param);
}
