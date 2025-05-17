package com.mercadolibre.api.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.mercadolibre.api.dto.ErrorResponseExampleDTO;
import com.mercadolibre.api.dto.ResponseExampleDTO;
import com.mercadolibre.model.exception.MyCustomException;
import com.mercadolibre.model.exception.codes.MyCustomPricingCodesEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class RestExampleRouter {

  private static final String DUMMY_RESOURCE = "/rest/resource";
  private static final String EXCEPTION_EXAMPLE = "/exception-example";

  @RouterOperation(
      //beanClass = HandlerClassName.class,
      //beanMethod = "handlerMethodName",
      path = "/rest/resource",
      produces = {
          MediaType.APPLICATION_JSON_VALUE},
      method = RequestMethod.GET,
      operation = @Operation(
          tags = "groupOfOperationsName",
          operationId = "operationIdName",
          summary = "Operation summary",
          description = "Operation description",
          parameters = {
              @Parameter(name = "queryParamOne", in = ParameterIn.QUERY, required = true),
              @Parameter(name = "queryParamTwo", in = ParameterIn.QUERY, required = true),
              @Parameter(name = "queryParamThree", in = ParameterIn.QUERY, required = true)
          },
          responses = {
              @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ResponseExampleDTO.class))),
              @ApiResponse(responseCode = "400", description = "Missing parameters", content = @Content(schema = @Schema(implementation = ErrorResponseExampleDTO.class)))}
      )
  )
  @Bean
  public RouterFunction<ServerResponse> restRouterFunction() {
    return route(GET(DUMMY_RESOURCE),
        req -> ServerResponse.ok().build())
        .andRoute(GET(EXCEPTION_EXAMPLE),
            req -> Mono.error(new MyCustomException(MyCustomPricingCodesEnum.MY_CUSTOM_PRICING_CODES_ENUM)));
  }

}
