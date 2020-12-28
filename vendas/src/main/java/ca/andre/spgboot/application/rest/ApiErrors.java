package ca.andre.spgboot.application.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {
	
	@Getter
	public List<String> errors;
	
	public ApiErrors(String messageErrors) {
		this.errors = Arrays.asList(messageErrors);
	}

}
