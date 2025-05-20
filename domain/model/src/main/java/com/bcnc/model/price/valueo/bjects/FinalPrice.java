package com.bcnc.model.price.valueo.bjects;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder(toBuilder = true)
public record FinalPrice(
    Long productId,
    Long brandId,
    Integer priceList,
    LocalDateTime startDate,
    LocalDateTime endDate,
    double price
) {
}