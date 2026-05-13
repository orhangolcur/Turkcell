package com.turkcell.spring_cqrs.core.security.authorization;

import java.util.Arrays;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.spring_cqrs.core.exception.UnauthenticatedException;
import com.turkcell.spring_cqrs.core.exception.UnauthorizedException;
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

    @Override
    public boolean supports(Object request) {
        return request instanceof AuthorizableRequest;
    }

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        if (!userContext.isAuthenticated())
            throw new UnauthenticatedException();

        RequiresRoles annotation = request.getClass().getAnnotation(RequiresRoles.class);
        if (annotation != null && annotation.value().length > 0) {
            boolean hasRole = Arrays.stream(annotation.value())
                    .anyMatch(role -> userContext.getRoles().contains(role));
            if (!hasRole)
                throw new UnauthorizedException();
        }

        return next.invoke();
    }
}
