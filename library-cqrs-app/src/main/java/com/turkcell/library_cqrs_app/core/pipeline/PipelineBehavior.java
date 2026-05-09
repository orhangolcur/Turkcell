package com.turkcell.library_cqrs_app.core.pipeline;

// Bu interface pipeline'daki her halkayı temsil ediyor. 
public interface PipelineBehavior {
    <R> R handle(Object request, RequestHandlerDelegate<R> next);

    // Varsayılan olarak true döner, yani her behavior her request için çalışır. Override edilirse seçici davranılabilir
    default boolean supports(Object request) {
        return true; 
    }
}
