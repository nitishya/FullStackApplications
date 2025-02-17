package com.bankapp.sbi.exception;

public class BadRequestException extends RuntimeException {

	// Declare the serialVersionUID field to resolve the serialization warning
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}
}
