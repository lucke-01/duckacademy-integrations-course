package nl.duckacademy.graphql.controller.config.directive;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.springframework.stereotype.Component;

@Component
public class UppercaseDirectiveWiring implements SchemaDirectiveWiring {

    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        DataFetcher<?> originalFetcher = environment.getFieldDataFetcher();
        DataFetcher<?> upperCaseFetcher = new DataFetcher<Object>() {
            @Override
            public Object get(DataFetchingEnvironment environment) throws Exception {
                Object value = originalFetcher.get(environment);
                if (value instanceof String valueString) {
                    return valueString.toUpperCase();
                }
                return value;
            }
        };
        environment.setFieldDataFetcher(upperCaseFetcher);
        return environment.getElement();
    }
}