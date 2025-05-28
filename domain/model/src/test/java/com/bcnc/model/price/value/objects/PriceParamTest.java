package com.bcnc.model.price.value.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class PriceParamTest {

  @Test
  void shouldConvertValidConsultationDateToLocalDateTime() {
    PriceParam priceParam = PriceParam.builder()
        .brandId(1L)
        .productId(100L)
        .consultationDate("2023-10-01 15:30:00")
        .build();

    assertEquals(LocalDateTime.of(2023, 10, 1, 15, 30, 0), priceParam.toLocalDateTime());
  }

  @Test
  void shouldAddDefaultTimeWhenDateHasNoTime() {
    PriceParam priceParam = PriceParam.builder()
        .brandId(1L)
        .productId(100L)
        .consultationDate("2023-10-01")
        .build();

    assertEquals(LocalDateTime.of(2023, 10, 1, 0, 0, 0), priceParam.toLocalDateTime());
  }

  @Test
  void shouldThrowExceptionForInvalidDateFormat() {
    PriceParam priceParam = PriceParam.builder()
        .brandId(1L)
        .productId(100L)
        .consultationDate("invalid-date")
        .build();

    RuntimeException exception = assertThrows(
        RuntimeException.class,
        priceParam::toLocalDateTime
    );

    assertNotNull(exception.getMessage());
  }
}