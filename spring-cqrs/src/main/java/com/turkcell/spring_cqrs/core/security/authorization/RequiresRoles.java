package com.turkcell.spring_cqrs.core.security.authorization;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) // Bu annotation'ın ne zaman mevcut olacağını belirtir.
public @interface RequiresRoles {
    String[] value();
}
