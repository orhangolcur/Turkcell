package com.turkcell.library_cqrs_app.core.logging;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.core.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs_app.core.pipeline.RequestHandlerDelegate;

@Component
@Order(20)
public class LoggingBehavior implements PipelineBehavior
{

    @Override
    public boolean supports(Object request) {
       return !(request instanceof NotLoggableRequest);
    }

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        System.out.println("loglama çalışıyor...");
        return next.invoke();
    }

}
