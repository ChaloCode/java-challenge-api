package com.bcnc.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.r2dbc.core.DatabaseClient;


@Configuration
@RequiredArgsConstructor
public class DataVerifier {

  private final DatabaseClient databaseClient;

  @EventListener(ApplicationReadyEvent.class)
  public void verifyData() {
    databaseClient.sql("SELECT * FROM prices")
        .fetch()
        .all()
        .doOnNext(row -> System.out.println("Row: " + row))
        .subscribe();
  }
}