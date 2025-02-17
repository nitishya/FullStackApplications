package com.bankapp.sbi.exception;

public class UnauthorizedException extends RuntimeException {

	// Declare the serialVersionUID field to resolve the serialization warning
	private static final long serialVersionUID = 1L;

	public UnauthorizedException(String message) {
		super(message);
	}
}
