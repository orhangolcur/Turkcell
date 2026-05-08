package com.turkcell.library_cqrs_app.core.pipeline;

@FunctionalInterface
public interface RequestHandlerDelegate<R> {
    R invoke();
}