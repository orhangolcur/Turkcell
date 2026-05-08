package com.turkcell.library_cqrs_app.core.pipeline;

public interface PipelineBehavior {
    <R> R handle(Object request, RequestHandlerDelegate<R> next);

    default boolean supports(Object request) {
        return true; 
    }
}
