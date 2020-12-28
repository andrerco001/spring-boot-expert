package ca.andre.spgboot.application.service;

import ca.andre.spgboot.application.domain.entity.Orders;
import ca.andre.spgboot.application.rest.dto.OrdersDTO;

public interface OrdersService {
	
	Orders save(OrdersDTO ordersDTO);

}
