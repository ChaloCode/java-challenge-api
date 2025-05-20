package com.bcnc.usecase.repository;

import com.bcnc.model.price.entities.Price;
import com.bcnc.model.price.value.objects.PriceParam;
import reactor.core.publisher.Flux;

public interface PriceRepository {
  Flux<Price> getPrices(PriceParam param);
}
