package com.bcnc.api.rest.health;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PingRest {

  public Mono<ServerResponse> ping() {
    return ServerResponse.ok().bodyValue("pong");
  }
}