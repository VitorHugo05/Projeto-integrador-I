package com.einstein.event.services.exceptions;

import org.springframework.http.HttpStatus;

public class InternalException extends RuntimeException {
    private final HttpStatus status;

    public InternalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
