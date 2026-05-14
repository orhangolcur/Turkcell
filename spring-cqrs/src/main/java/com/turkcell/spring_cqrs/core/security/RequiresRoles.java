package com.turkcell.spring_cqrs.core.security;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRoles {
    String[] value();
}
