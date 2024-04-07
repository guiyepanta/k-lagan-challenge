package com.klagan.challenge.adapter.in.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class AdviceExceptionController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> RuntimeExceptionHandler(Exception exception) {
	ErrorResponse error = ErrorResponse.builder().message(exception.getMessage()).build();
	return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> RuntimeExceptionHandler(RuntimeException exception) {
	ErrorResponse error = ErrorResponse.builder().message(exception.getMessage()).build();
	return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResponse> RuntimeExceptionHandler(BadRequestException exception) {
	ErrorResponse error = ErrorResponse.builder().message(exception.getMessage()).build();
	return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException ex) {

	final List<String> errors = new ArrayList<String>();
	for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
	    errors.add(error.getField() + ": " + error.getDefaultMessage());
	}
	return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
