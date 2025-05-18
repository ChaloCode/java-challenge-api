package com.bcnc.api.rest.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.bcnc.api.exception.dto.CustomErrorResponse;
import com.bcnc.api.rest.health.PingRest;
import com.bcnc.api.rest.price.PriceController;
import com.bcnc.api.rest.price.dto.FinalPriceResponse;
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

@Configuration
@RequiredArgsConstructor
public class RestProjectRouter {

  private static final String HEALTH_PING = "/api/ping";
  private static final String GET_PRICE = "/api/brands/{brandId}/products/{productId}/prices/{consultationDate}";

  private final PingRest pingRest;
  private final PriceController priceController;

  @RouterOperation(
      path = "/api/brands/{brandId}/products/{productId}/prices/{consultationDate}",
      produces = {MediaType.APPLICATION_JSON_VALUE},
      method = RequestMethod.GET,
      operation = @Operation(
          tags = "Prices",
          operationId = "getPrice",
          summary = "Obtener el precio de un producto para una marca en una fecha específica",
          description = "Devuelve el precio más relevante para un producto y marca en una fecha dada.",
          parameters = {
              @Parameter(name = "brandId", in = ParameterIn.PATH, required = true, description = "ID de la marca"),
              @Parameter(name = "productId", in = ParameterIn.PATH, required = true, description = "ID del producto"),
              @Parameter(name = "consultationDate", in = ParameterIn.PATH, required = true, description = "Fecha de consulta en formato yyyy-MM-dd o yyyy-MM-dd HH:mm:ss")
          },
          responses = {
              @ApiResponse(responseCode = "200", description = "Operación exitosa, se devuelve el precio", content = @Content(schema = @Schema(implementation = FinalPriceResponse.class))),
              @ApiResponse(responseCode = "204", description = "No hay contenido, no se encontró un precio relevante"),
              @ApiResponse(responseCode = "400", description = "Solicitud inválida, parámetros incorrectos", content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))),
              @ApiResponse(responseCode = "404", description = "No se encontró el recurso solicitado"),
              @ApiResponse(responseCode = "500", description = "Error interno del servidor")
          }
      )
  )
  @Bean
  public RouterFunction<ServerResponse> restRouterFunction() {
    return route(GET(HEALTH_PING), req -> pingRest.ping())
        .andRoute(GET(GET_PRICE), priceController::getPrice);
  }

}

