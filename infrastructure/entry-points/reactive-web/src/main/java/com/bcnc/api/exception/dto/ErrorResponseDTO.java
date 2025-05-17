package com.mercadolibre.api.exception.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record ErrorResponseDTO(String code, String message) {
}
