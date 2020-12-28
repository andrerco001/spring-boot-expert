package ca.andre.spgboot.application.service;

import java.util.Optional;

import ca.andre.spgboot.application.domain.entity.Orders;
import ca.andre.spgboot.application.domain.enums.StatusOrders;
import ca.andre.spgboot.application.rest.dto.OrdersDTO;

public interface OrdersService {
	
	Orders save(OrdersDTO ordersDTO);
	
	Optional<Orders> getCompletOrders(Integer id);
	
	void updateStatus(Integer id, StatusOrders statusOrders);

}
