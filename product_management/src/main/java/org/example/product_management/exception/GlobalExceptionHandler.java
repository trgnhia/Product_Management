package org.example.product_management.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.product_management.dto.exception.response.ExceptionResponse;
import org.example.product_management.dto.exception.response.ValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFound(ResourceNotFoundException e) {
        log.warn("Resource not found {}", e.getMessage());
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                status.value(),
                status.getReasonPhrase()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler (value = ResourceConflictException.class)
    public ResponseEntity<ExceptionResponse> handleResourceConflictException (ResourceConflictException e) {
        log.warn("Resource conflict {}", e.getMessage());
        HttpStatus status = HttpStatus.CONFLICT;
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                status.value(),
                status.getReasonPhrase()
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler (value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationResponse> handleMethodArgumentNotValidException (MethodArgumentNotValidException e) {
        log.warn("Validation failed {}", e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String,String> errors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ValidationResponse response = new ValidationResponse (
                "Validation failed",
                status.value(),
                status.getReasonPhrase(),
                errors
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(value = InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidCredentialsException(InvalidCredentialsException e) {
        log.warn("Invalid credentials {}", e.getMessage());
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                status.value(),
                status.getReasonPhrase()
        );
        return ResponseEntity.status(status).body(response);
    }
}
