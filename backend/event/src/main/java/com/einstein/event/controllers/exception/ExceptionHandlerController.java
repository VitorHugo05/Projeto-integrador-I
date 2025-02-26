package com.einstein.event.controllers.exception;


import com.einstein.event.services.exceptions.DataAlreadyExistException;
import com.einstein.event.services.exceptions.IncorrectCredentialsException;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<StandardError> badCredentials(HttpServletRequest request, IncorrectCredentialsException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Credentials is incorrect", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(HttpServletRequest request, ObjectNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Object not found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<StandardError> dataAlreadyExist(HttpServletRequest request, DataAlreadyExistException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Data already exist", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
