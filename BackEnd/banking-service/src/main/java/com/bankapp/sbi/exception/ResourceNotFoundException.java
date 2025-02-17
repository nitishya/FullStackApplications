package com.bankapp.sbi.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    // Declare the serialVersionUID field to resolve the serialization warning
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
