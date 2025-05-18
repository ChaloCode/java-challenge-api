package com.bcnc.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder(toBuilder = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ResponseExampleDTO(Long brandId,
                                 LocalDateTime startDate,
                                 LocalDateTime endDate,
                                 Integer priceList,
                                 Long productId,
                                 int priority,
                                 double price,
                                 String currency) {
}
