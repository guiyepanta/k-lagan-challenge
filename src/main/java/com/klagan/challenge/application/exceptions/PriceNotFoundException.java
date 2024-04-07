package com.klagan.challenge.application.exceptions;

public class PriceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;

    public PriceNotFoundException(String message) {
	this.message = message;
    }

    public String getMessage() {
	return message;
    }
}