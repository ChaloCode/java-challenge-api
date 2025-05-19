package com.bcnc.usecase.repository;

import com.bcnc.model.price.Price;
import com.bcnc.model.price.PriceParam;
import reactor.core.publisher.Flux;

public interface PriceRepository {
  Flux<Price> getPrices(PriceParam param);
}
