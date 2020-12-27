package ca.andre.spgboot.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.andre.spgboot.application.domain.repository.OrdersRepository;
import ca.andre.spgboot.application.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	
	@Autowired
	private OrdersRepository ordersRepository;

	public OrdersServiceImpl(OrdersRepository ordersRepository) {
		this.ordersRepository = ordersRepository;
	}
	
	

}
