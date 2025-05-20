package com.bcnc.model.price.entities;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder(toBuilder = true)
public record Price(
    Long brandId,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer priceList,
    Long productId,
    int priority,
    double price,
    String currency
) {
}