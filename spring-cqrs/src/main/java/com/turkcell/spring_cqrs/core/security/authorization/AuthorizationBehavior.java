package com.turkcell.spring_cqrs.core.security.authorization;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.turkcell.spring_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.spring_cqrs.core.mediator.pipeline.RequestHandlerDelegate;
import com.turkcell.spring_cqrs.core.security.context.UserContext;

@Component
@Order(10)
public class AuthorizationBehavior implements PipelineBehavior {

    private final UserContext userContext;

    public AuthorizationBehavior(UserContext userContext) {
        this.userContext = userContext;
    }

    // ilgili handler'ın öncesi ve sonrası çalıştırabilen kodlar.
    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        if(!userContext.isAuthenticated())
            throw new RuntimeException("Giriş yapmalısın..");
            // todo: özel bir exception fırlat
            // Handler da bu exception'ı eğer giriş yapılmışsa 401, (UnauthenticationException)
            // yapılmış ancak rol yetersiz ise 403 döndürecek şekilde (UnauthorizedException)
        return next.invoke(); // zincirdeki sonraki halkayı çağır..
    }
}
