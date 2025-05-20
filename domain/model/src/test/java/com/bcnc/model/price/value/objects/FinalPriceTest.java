package com.bcnc.model.price.value.objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class FinalPriceTest {

  @Test
  void shouldCreateFinalPriceWithCorrectValues() {
    FinalPrice finalPrice = FinalPrice.builder()
        .productId(100L)
        .brandId(1L)
        .priceList(1)
        .startDate(LocalDateTime.of(2023, 10, 1, 0, 0))
        .endDate(LocalDateTime.of(2023, 10, 2, 0, 0))
        .price(50.0)
        .build();

    assertAll(
        () -> assertEquals(100L, finalPrice.productId()),
        () -> assertEquals(1L, finalPrice.brandId()),
        () -> assertEquals(1, finalPrice.priceList()),
        () -> assertEquals(LocalDateTime.of(2023, 10, 1, 0, 0), finalPrice.startDate()),
        () -> assertEquals(LocalDateTime.of(2023, 10, 2, 0, 0), finalPrice.endDate()),
        () -> assertEquals(50.0, finalPrice.price())
    );
  }
}