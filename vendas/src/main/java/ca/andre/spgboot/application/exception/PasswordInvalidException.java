package ca.andre.spgboot.application.exception;

public class PasswordInvalidException extends RuntimeException {

	public PasswordInvalidException() {
		super("The pasword is invalid. Enter a valid password.");
	}

}
