package com.turkcell.spring_cqrs.core.exception;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException() {
        super("Giriş yapmanız gerekmektedir.");
    }

    public UnauthenticatedException(String message) {
        super(message);
    }
}
