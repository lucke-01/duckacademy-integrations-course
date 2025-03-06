package nl.duckacademy.graphql.controller.exceptionmanager;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import nl.duckacademy.graphql.exception.NotFoundException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof NotFoundException notFoundException) {
            return GraphQLError.newError()
                    .errorType(ErrorType.NOT_FOUND)
                    .message(notFoundException.getMessage() + " desc: " + notFoundException.getDescription())
                    .path(env.getExecutionStepInfo().getPath())
                    .location(env.getField().getSourceLocation())
                    .build();
        } else {
            return null;
        }
    }
}
