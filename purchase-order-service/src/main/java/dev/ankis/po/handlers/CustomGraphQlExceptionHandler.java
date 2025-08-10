package dev.ankis.po.handlers;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;


@Component
public class CustomGraphQlExceptionHandler implements DataFetcherExceptionHandler {

    @Override
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
        Throwable exception = handlerParameters.getException();
        GraphQLError error = GraphqlErrorBuilder.newError()
                .message(exception.getMessage())
                .path(handlerParameters.getPath())
                .location(handlerParameters.getSourceLocation())
                .build();
        DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult().error(error).build();
        return CompletableFuture.completedFuture(result);
    }
}
