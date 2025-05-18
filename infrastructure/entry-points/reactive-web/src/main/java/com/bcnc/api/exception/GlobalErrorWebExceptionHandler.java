package com.bcnc.api.exception;


import com.bcnc.api.exception.dto.CustomErrorResponse;
import com.bcnc.model.exception.MyCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
@Slf4j
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

  public GlobalErrorWebExceptionHandler(
      ErrorAttributes errorAttributes,
      WebProperties.Resources resources,
      ApplicationContext applicationContext,
      ServerCodecConfigurer configurer) {
    super(errorAttributes, resources, applicationContext);
    this.setMessageWriters(configurer.getWriters());
    this.setMessageReaders(configurer.getReaders());
  }

  @Override
  protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
    return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
  }

  private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
    return Mono.defer(() -> Mono.error(getError(request))
        .onErrorResume(MyCustomException.class, this::handleMyCustomException)
        .onErrorResume(NoResourceFoundException.class, this::handleNoResourceFoundException)
        .onErrorResume(this::handleGenericException)
    ).cast(ServerResponse.class);
  }

  private Mono<ServerResponse> handleMyCustomException(MyCustomException myCustomException) {
    log.warn("MyCustomException: {}", myCustomException.getLocalizedMessage());
    return buildErrorResponse(myCustomException.getPricingCodesEnum().getErrorCode(), myCustomException.getMessage(),
        HttpStatus.valueOf(myCustomException.getPricingCodesEnum().getStatusCode()));
  }

  private Mono<ServerResponse> handleNoResourceFoundException(NoResourceFoundException noResourceFoundException) {
    log.warn("NoResourceFoundException: {}", noResourceFoundException.getLocalizedMessage());
    return buildErrorResponse("404", "Resource not found", HttpStatus.NOT_FOUND);
  }

  private Mono<ServerResponse> handleGenericException(Throwable exception) {
    log.error("Unexpected error {}", exception.getLocalizedMessage());
    return buildErrorResponse("500", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private Mono<ServerResponse> buildErrorResponse(String errorCode, String message, HttpStatus status) {
    return ServerResponse.status(status)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(CustomErrorResponse.builder()
            .code(errorCode)
            .message(message)
            .build()));
  }
}