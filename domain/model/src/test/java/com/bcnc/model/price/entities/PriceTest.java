package com.bcnc.model.price.entities;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class PriceTest {

  private static final Long BRAND_ID = 1L;
  private static final LocalDateTime START_DATE = LocalDateTime.of(2023, 10, 1, 0, 0);
  private static final LocalDateTime END_DATE = LocalDateTime.of(2023, 10, 2, 0, 0);
  private static final Integer PRICE_LIST = 1;
  private static final Long PRODUCT_ID = 100L;
  private static final int PRIORITY = 1;
  private static final double PRICE = 50.0;
  private static final String CURRENCY_EUR = "EUR";
  private static final double MODIFIED_PRICE = 60.0;
  private static final String CURRENCY_USD = "USD";

  @Test
  void shouldCreatePriceWithCorrectValues() {
    Price priceEntity = Price.builder()
        .brandId(BRAND_ID)
        .startDate(START_DATE)
        .endDate(END_DATE)
        .priceList(PRICE_LIST)
        .productId(PRODUCT_ID)
        .priority(PRIORITY)
        .price(PRICE)
        .currency(CURRENCY_EUR)
        .build();

    assertAll(
        () -> assertEquals(BRAND_ID, priceEntity.brandId()),
        () -> assertEquals(START_DATE, priceEntity.startDate()),
        () -> assertEquals(END_DATE, priceEntity.endDate()),
        () -> assertEquals(PRICE_LIST, priceEntity.priceList()),
        () -> assertEquals(PRODUCT_ID, priceEntity.productId()),
        () -> assertEquals(PRIORITY, priceEntity.priority()),
        () -> assertEquals(PRICE, priceEntity.price()),
        () -> assertEquals(CURRENCY_EUR, priceEntity.currency())
    );
  }

  @Test
  void shouldAllowModificationUsingToBuilder() {
    Price originalPrice = Price.builder()
        .brandId(BRAND_ID)
        .startDate(START_DATE)
        .endDate(END_DATE)
        .priceList(PRICE_LIST)
        .productId(PRODUCT_ID)
        .priority(PRIORITY)
        .price(PRICE)
        .currency(CURRENCY_EUR)
        .build();

    Price modifiedPrice = originalPrice.toBuilder()
        .price(MODIFIED_PRICE)
        .currency(CURRENCY_USD)
        .build();

    assertAll(
        () -> assertEquals(MODIFIED_PRICE, modifiedPrice.price()),
        () -> assertEquals(CURRENCY_USD, modifiedPrice.currency()),
        () -> assertEquals(originalPrice.brandId(), modifiedPrice.brandId()),
        () -> assertEquals(originalPrice.startDate(), modifiedPrice.startDate()),
        () -> assertEquals(originalPrice.endDate(), modifiedPrice.endDate()),
        () -> assertEquals(originalPrice.priceList(), modifiedPrice.priceList()),
        () -> assertEquals(originalPrice.productId(), modifiedPrice.productId()),
        () -> assertEquals(originalPrice.priority(), modifiedPrice.priority())
    );
  }
}