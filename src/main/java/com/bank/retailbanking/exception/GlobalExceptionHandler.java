package com.bank.retailbanking.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value=GeneralException.class)
	public ResponseEntity<ErrorResponse> userException(GeneralException exception){
		return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), LocalDate.now()), HttpStatus.NOT_FOUND);	
	}
}
