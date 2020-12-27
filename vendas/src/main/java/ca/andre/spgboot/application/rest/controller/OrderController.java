package ca.andre.spgboot.application.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.andre.spgboot.application.service.OrdersService;

@RestController
@RequestMapping("api/order")
public class OrderController {
	
	private OrdersService ordersService;

	public OrderController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}
	
	
	
	
	
	

}
