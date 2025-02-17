package com.bankapp.sbi.exception;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Global Exception Handling
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle Resource Not Found Exception (404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle Bad Request Exception (400)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequestException(BadRequestException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle Unauthorized Access Exception (401)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedException(UnauthorizedException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // Handle Generic Exception (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("message", "An unexpected error occurred.");
        errorResponse.put("error", ex.getMessage());
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}