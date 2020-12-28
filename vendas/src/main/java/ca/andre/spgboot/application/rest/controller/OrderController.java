package ca.andre.spgboot.application.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.andre.spgboot.application.domain.entity.Orders;
import ca.andre.spgboot.application.rest.dto.OrdersDTO;
import ca.andre.spgboot.application.service.OrdersService;

@RestController
@RequestMapping("api/order")
public class OrderController {
	
	private OrdersService ordersService;

	public OrderController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody OrdersDTO ordersDTO) {
		
		Orders orders = ordersService.save(ordersDTO);
		
		return orders.getId();
	}
	
	
	
	
	
	

}
