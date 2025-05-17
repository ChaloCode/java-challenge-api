package com.bcnc;

import com.bcnc.model.utils.Generated;
import com.bcnc.model.utils.ScopeUtils;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Generated
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "api-name",
        version = "1.0.0",
        description = "API description"
    ),
    servers = {
        @Server(
            url = "http://domain-of-your-app.melisystems.com",
            description = "scope = dev | test"),
        @Server(
            url = "http://domain-of-your-app.melisystems.com",
            description = "scope = prod"),
        @Server(
            url = "https://domain-of-your-app.melioffice.com",
            description = "Server to call from local machine. scope = dev | test"),
        @Server(
            url = "http://localhost:8080",
            description = "Local machine for development purposes")
    }
)
public class MainApplication {
  public static void main(String[] args) {
    ScopeUtils.calculateScopeSuffix();
    SpringApplication.run(MainApplication.class, args);
  }
}
