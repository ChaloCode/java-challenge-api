package com.bcnc.usecase.price;

import com.bcnc.model.price.value.objects.FinalPrice;
import com.bcnc.model.price.value.objects.PriceParam;
import com.bcnc.model.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PriceUseCase {

  private final PriceRepository priceRepository;

  public Mono<FinalPrice> execute(PriceParam priceParam) {
    return priceRepository.getPrices(priceParam)
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
