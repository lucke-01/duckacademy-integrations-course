package nl.duckacademy.rest_api.config;

import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPIConfig() {
        return new OpenAPI().info(
                new Info().title("API DOCS").description("Descripcion").version("1.0.0")
        );
    }
}
