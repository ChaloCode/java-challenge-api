package com.bcnc.api.exception.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record CustomErrorResponse(String code, String message) {
}
