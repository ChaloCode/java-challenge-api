package com.bcnc.r2dbc.config;

import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Slf4j
@Configuration
@EnableR2dbcRepositories
public class H2Configuration extends AbstractR2dbcConfiguration {

  public static final String DB_NAME = "test_db";

  @Override
  @Bean
  public ConnectionFactory connectionFactory() {
    return H2ConnectionFactory.inMemory(DB_NAME);
  }
}