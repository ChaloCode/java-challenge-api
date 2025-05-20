package com.bcnc.usecase.price;

import com.bcnc.model.price.valueo.bjects.FinalPrice;
import com.bcnc.model.price.entities.Price;
import com.bcnc.model.price.valueo.bjects.PriceParam;
import com.bcnc.usecase.repository.PriceRepository;
import java.util.Comparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class PriceUseCase {
  private final PriceRepository priceRepository;

  public Mono<FinalPrice> execute(PriceParam priceParam) {
    return priceRepository.getPrices(priceParam)
        .sort(Comparator.comparingInt(Price::priority).reversed())
        .next()
        .map(price -> FinalPrice.builder()
            .productId(price.productId())
            .brandId(price.brandId())
            .priceList(price.priceList())
            .startDate(price.startDate())
            .endDate(price.endDate())
            .price(price.price())
            .build());
  }
}
