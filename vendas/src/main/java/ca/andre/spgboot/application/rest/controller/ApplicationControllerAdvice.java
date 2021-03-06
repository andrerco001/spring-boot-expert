package ca.andre.spgboot.application.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ca.andre.spgboot.application.exception.BusinessRulesException;
import ca.andre.spgboot.application.exception.OrderNotFoundException;
import ca.andre.spgboot.application.rest.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(BusinessRulesException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handlerBusinessRulerException(BusinessRulesException ex) {
		String messageError = ex.getMessage();
		return new ApiErrors(messageError);
	 }
	
	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handlerOrderNotFoundException(OrderNotFoundException ex) {
		return new ApiErrors(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handlerMethodNotValidException(MethodArgumentNotValidException ex) {
		
		List<String> errors = ex.getBindingResult().getAllErrors()
				.stream()
				.map( error -> error.getDefaultMessage())
				.collect(Collectors.toList());
		return new ApiErrors(errors);
	}

}
