package com.turkcell.library_cqrs_app.core.logging;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.core.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs_app.core.pipeline.RequestHandlerDelegate;

import tools.jackson.databind.ObjectMapper;

@Component
@Order(30)
public class LoggingBehavior implements PipelineBehavior
{
    // Java nesnesini JSON string'e çeviriyor
    private final ObjectMapper objectMapper;

    public LoggingBehavior(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(Object request) {
       return !(request instanceof NotLoggableRequest);
    }

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        try {
            String requestJson = objectMapper.writeValueAsString(request);
            System.out.println("[REQUEST] " + request.getClass().getSimpleName()
                + " -> " + requestJson);

            R result = next.invoke();

            String responseJson = objectMapper.writeValueAsString(result);
            System.out.println("[RESPONSE] " + request.getClass().getSimpleName()
                + " -> " + responseJson);

            return result;

        } catch (Exception e) {
            System.out.println("[LOGGING HATASI] " + e.getMessage());
        return next.invoke(); // bu satır zinciri devam ettiriyor
        }
    }

}
