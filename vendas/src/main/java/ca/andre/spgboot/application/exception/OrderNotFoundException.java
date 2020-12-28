package ca.andre.spgboot.application.exception;

public class OrderNotFoundException extends RuntimeException {

	public OrderNotFoundException() {
		super("Order not found!");
	}
}
