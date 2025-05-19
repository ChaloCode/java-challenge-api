package com.bcnc.r2dbc.config;

import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class H2Configuration extends AbstractR2dbcConfiguration {

  private String DB_NAME;

  public H2Configuration() {
    this.DB_NAME = System.getenv("DB_NAME") == null ? "test_db" : System.getenv("DB_NAME");
  }

  @Override
  @Bean
  public ConnectionFactory connectionFactory() {
    return H2ConnectionFactory.inMemory(DB_NAME);
  }
}