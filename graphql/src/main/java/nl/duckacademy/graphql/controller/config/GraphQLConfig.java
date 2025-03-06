package nl.duckacademy.graphql.controller.config;

import graphql.schema.GraphQLScalarType;
import nl.duckacademy.graphql.controller.config.directive.UppercaseDirectiveWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(GraphQLScalarType localDateTypeScalar,
                                                           UppercaseDirectiveWiring uppercaseDirectiveWiring) {
        return wiringBuilder -> wiringBuilder
                .directive("uppercase", uppercaseDirectiveWiring)
                .scalar(localDateTypeScalar);
    }
}
