package nl.duckacademy.graphql.controller.config.scalar;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.*;
import jakarta.annotation.Nonnull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class ScalarConfiguration {
    @Bean
    public GraphQLScalarType localDateTypeScalar() {
        return GraphQLScalarType.newScalar()
                .name("LocalDateTime")
                .description("A custom scalar that handles Java 8 LocalDateTime")
                .coercing(new Coercing<LocalDateTime, String>() {
                    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

                    //TO SEND AS RESPONSE
                    @Override
                    public String serialize(@Nonnull Object dataFetcherResult, @Nonnull GraphQLContext graphQLContext,
                                            @Nonnull Locale locale) {
                        if (dataFetcherResult instanceof LocalDateTime localDateTime) {
                            return localDateTime.format(formatter);
                        }
                        throw new CoercingSerializeException("Expected a LocalDateTime object.");
                    }

                    //PASSING VALUE IN VARIABLE SECTION
                    @Override
                    public LocalDateTime parseValue(@Nonnull Object input, @Nonnull GraphQLContext graphQLContext,
                                                    @Nonnull Locale locale) {
                        if (input instanceof String inputString) {
                            return LocalDateTime.parse(inputString, formatter);
                        }
                        throw new CoercingParseValueException("Expected a string.");
                    }

                    //PASSING VALUE WITHOUT VARIABLE
                    @Override
                    public LocalDateTime parseLiteral(@Nonnull Value input,
                                                      @Nonnull CoercedVariables variables,
                                                      @Nonnull GraphQLContext graphQLContext,
                                                      @Nonnull Locale locale) {
                        if (input instanceof StringValue inputStringValue) {
                            return LocalDateTime.parse(inputStringValue.getValue(), formatter);
                        }
                        throw new CoercingParseLiteralException("Expected a string literal.");
                    }
                })
                .build();

    }


}
