package com.bcnc.api.rest.price.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder(toBuilder = true)
public record PriceParamRequest(
    @NotNull(message = "brandId no puede ser nulo")
    @Pattern(regexp = "^[1-9]\\d*$", message = "brandId debe ser numérico y mayor a 0")
    String brandId,
    @NotNull(message = "productId no puede ser nulo")
    @Pattern(regexp = "^[1-9]\\d*$", message = "productId debe ser numérico y mayor a 0")
    String productId,
    @NotNull(message = "consultationDate no puede ser nulo")
    @Pattern(
        regexp = "^\\d{4}-\\d{2}-\\d{2}( \\d{2}:\\d{2}:\\d{2})?$",
        message = "consultationDate debe tener formato yyyy-mm-dd o yyyy-mm-dd hh:mm:ss"
    )
    String consultationDate
) {
}
