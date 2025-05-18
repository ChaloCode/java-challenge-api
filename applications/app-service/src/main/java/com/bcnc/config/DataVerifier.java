package com.bcnc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.r2dbc.core.DatabaseClient;


@Configuration
public class DataVerifier {

  @Autowired
  private DatabaseClient databaseClient;

  @EventListener(ApplicationReadyEvent.class)
  public void verifyData() {
    databaseClient.sql("SELECT * FROM prices")
        .fetch()
        .all()
        .doOnNext(row -> System.out.println("Row: " + row))
        .subscribe();
  }
}