package com.turkcell.library_cqrs_app.core.security.auhorization;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.turkcell.library_cqrs_app.core.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs_app.core.pipeline.RequestHandlerDelegate;

@Component
@Order(20)
public class AuthorizationBehavior implements PipelineBehavior{

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        System.out.println("AuthorizationBehavior çalışıyor...");
        return next.invoke(); // zincirdeki sonraki halkayı çağırır.
    }

}
