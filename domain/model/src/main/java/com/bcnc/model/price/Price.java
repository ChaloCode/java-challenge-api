package com.bcnc.model.price;

import java.time.LocalDateTime;

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