package ca.andre.spgboot.application.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ca.andre.spgboot.application.exception.BusinessRulesException;
import ca.andre.spgboot.application.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(BusinessRulesException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handlerBusinessRulerException(BusinessRulesException ex) {
		String messageError = ex.getMessage();
		return new ApiErrors(messageError);
	 }

}
