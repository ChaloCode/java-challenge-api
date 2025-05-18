package com.bcnc.r2dbc.config;

import static org.assertj.core.api.Assertions.assertThat;

import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class H2ConfigurationTest {

  private ConnectionFactory factory;

  @BeforeEach
  void setUp() {
    H2Configuration config;
    config = new H2Configuration();
    factory = config.connectionFactory();
  }

  @Test
  void shouldCreateConnectionFactory() {
    assertThat(factory).isNotNull();
    assertThat(factory.getClass().getName()).contains("H2ConnectionFactory");
  }

  @Test
  void shouldUseCorrectDatabaseName() {
    String factoryString = factory.toString();
    assertThat(factoryString).contains(H2Configuration.DB_NAME);
  }
}