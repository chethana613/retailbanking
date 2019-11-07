package com.hcl.mediclaim.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	
	public ResponseEntity<ErrorResponse> PolicyExpiredException(Exception exception,WebRequest request){
	    ErrorResponse error = new ErrorResponse(exception.getMessage(), LocalDate.now());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<ErrorResponse> UserException(Exception exception,WebRequest request){
		ErrorResponse error= new ErrorResponse(exception.getMessage(), LocalDate.now());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
}
