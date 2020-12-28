package ca.andre.spgboot.application.rest.controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ca.andre.spgboot.application.domain.entity.ItenOrders;
import ca.andre.spgboot.application.domain.entity.Orders;
import ca.andre.spgboot.application.domain.enums.StatusOrders;
import ca.andre.spgboot.application.rest.dto.ItenOrdersInformationDTO;
import ca.andre.spgboot.application.rest.dto.OrderInformationsDTO;
import ca.andre.spgboot.application.rest.dto.OrdersDTO;
import ca.andre.spgboot.application.rest.dto.UpdateStatusOrderDTO;
import ca.andre.spgboot.application.service.OrdersService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private OrdersService ordersService;

	public OrderController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody @Valid OrdersDTO ordersDTO) {
		
		Orders orders = ordersService.save(ordersDTO);
		
		return orders.getId();
	}
	
	@GetMapping("{id}")
	public OrderInformationsDTO getById(@PathVariable("id") Integer id) {
		return ordersService.getCompletOrders(id)
				.map(p -> convertOrderInformationsDTO(p))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!"));
	}
	
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateStatus(@PathVariable("id") Integer id, @RequestBody UpdateStatusOrderDTO updateStatusOrderDto) {
		
		String newStatuString = updateStatusOrderDto.getNewStatus();
		ordersService.updateStatus(id, StatusOrders.valueOf(newStatuString));
	}
	
	private OrderInformationsDTO convertOrderInformationsDTO(Orders orders) {
		return OrderInformationsDTO
		.builder()
		.code(orders.getId())
		.dateOrders(orders.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
		.cpf(orders.getCustomer().getCpf())
		.nameCustomer(orders.getCustomer().getName())
		.total(orders.getTotal())
		.status(orders.getStatus().name())
		.itenOrdersInformationDTO(convertListOrderInformationsDTOs(orders.getItenOrders()))
		.build();
	}
	
	private List<ItenOrdersInformationDTO> convertListOrderInformationsDTOs(List<ItenOrders> itens){
		
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream()
				.map(iten -> ItenOrdersInformationDTO.builder().productDescription(iten.getProduct().getDescription())
				.unityPrice(iten.getProduct().getPrice())
				.quantity(iten.getQuantity())
				.build()
				).collect(Collectors.toList());
	}

}
