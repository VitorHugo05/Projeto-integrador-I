package com.einstein.event.services.exceptions;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends RuntimeException {
    private final HttpStatus status;

    public AccessDeniedException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
