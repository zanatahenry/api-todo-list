package com.zanatahenry.TodoList.docs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI openAPI () {
    return new OpenAPI()
      .info(new Info()
        .title("Documentation - Rest API")
        .description("Documentation - Rest API")
        .version("1.0")
        .termsOfService("Open Source")
        .license(new License()
          .name("Apache 2.0")
          .url("agxsoftware.com")
        )
      ).externalDocs(new ExternalDocumentation()
        .description("Henry Zanata")
        .url("agxsoftware.com")
      );
  }
}
