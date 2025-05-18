package com.bcnc.api.rest.price.mapper;

import com.bcnc.api.rest.price.dto.PriceParamRequest;
import java.util.function.Function;
import org.springframework.web.reactive.function.server.ServerRequest;

public final class PriceParamRequestMapper implements Function<ServerRequest, PriceParamRequest> {

  @Override
  public PriceParamRequest apply(ServerRequest serverRequest) {
    return PriceParamRequest.builder()
        .brandId(serverRequest.pathVariable("brandId"))
        .productId(serverRequest.pathVariable("productId"))
        .consultationDate(serverRequest.pathVariable("consultationDate"))
        .build();
  }

  public static PriceParamRequest toMap(ServerRequest serverRequest) {
    return new PriceParamRequestMapper().apply(serverRequest);
  }
}

