package com.einstein.event.services.exceptions;

public class BadRequestArgumentException extends RuntimeException {
    public BadRequestArgumentException(String message) {
        super(message);
    }
}
