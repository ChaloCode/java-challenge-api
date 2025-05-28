package com.bcnc.api.rest.price;

import com.bcnc.api.exception.RequestValidator;
import com.bcnc.api.rest.price.mapper.FinalPriceResponseMapper;
import com.bcnc.api.rest.price.mapper.PriceParamMapper;
import com.bcnc.api.rest.price.mapper.PriceParamRequestMapper;
import com.bcnc.exception.codes.MyCustomCodes;
import com.bcnc.usecase.price.PriceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class PriceController {

  private final PriceParamMapper priceParamMapper;
  private final FinalPriceResponseMapper finalPriceResponseMapper;
  private final PriceUseCase priceUseCase;

  public Mono<ServerResponse> getPrice(ServerRequest serverRequest) {
    return Mono.fromSupplier(() -> PriceParamRequestMapper.toMap(serverRequest))
        .map(request -> RequestValidator.validate(request, MyCustomCodes.ERROR_VALIDATING_REQUEST))
        .map(priceParamMapper::toModel)
        .flatMap(priceUseCase::execute)
        .map(finalPriceResponseMapper::toResponse)
        .flatMap(response -> ServerResponse.ok().bodyValue(response))
        .switchIfEmpty(ServerResponse.badRequest().build());
  }

}
