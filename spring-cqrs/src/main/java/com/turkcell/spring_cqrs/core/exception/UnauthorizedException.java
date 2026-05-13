package com.turkcell.spring_cqrs.core.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Bu işlem için yetkiniz bulunmamaktadır.");
    }
}
