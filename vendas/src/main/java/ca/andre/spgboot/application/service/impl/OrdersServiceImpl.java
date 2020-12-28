package ca.andre.spgboot.application.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ca.andre.spgboot.application.domain.entity.Customer;
import ca.andre.spgboot.application.domain.entity.ItenOrders;
import ca.andre.spgboot.application.domain.entity.Orders;
import ca.andre.spgboot.application.domain.entity.Product;
import ca.andre.spgboot.application.domain.enums.StatusOrders;
import ca.andre.spgboot.application.domain.repository.CustomerRepository;
import ca.andre.spgboot.application.domain.repository.ItenOrdersRepository;
import ca.andre.spgboot.application.domain.repository.OrdersRepository;
import ca.andre.spgboot.application.domain.repository.ProductRepository;
import ca.andre.spgboot.application.exception.BusinessRulesException;
import ca.andre.spgboot.application.exception.OrderNotFoundException;
import ca.andre.spgboot.application.rest.dto.ItenOrdersDTO;
import ca.andre.spgboot.application.rest.dto.OrdersDTO;
import ca.andre.spgboot.application.service.OrdersService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

	private final OrdersRepository ordersRepository;

	private final CustomerRepository customerRepository;

	private final ProductRepository productRepository;

	private final ItenOrdersRepository itenOrdersRepository;

	@Override
	@Transactional
	public Orders save(OrdersDTO ordersDTO) {
		Integer idCustomer = ordersDTO.getCustomer();
		Customer customer = customerRepository.findById(idCustomer)
				.orElseThrow(() -> new BusinessRulesException("Invalid customer code!"));

		Orders orders = new Orders();
		orders.setTotal(ordersDTO.getTotal());
		orders.setOrderDate(LocalDate.now());
		orders.setCustomer(customer);
		orders.setStatus(StatusOrders.ACCOMPLISHED);

		List<ItenOrders> itenOrders = convertItens(orders, ordersDTO.getItens());
		ordersRepository.save(orders);
		itenOrdersRepository.saveAll(itenOrders);
		orders.setItenOrders(itenOrders);
		return orders;
	}

	private List<ItenOrders> convertItens(Orders orders, List<ItenOrdersDTO> itens) {

		if (itens.isEmpty()) {
			throw new BusinessRulesException("It is not possible to place an order without items!");
		}

		return itens.stream().map(dto -> {
			Integer idProduct = dto.getProduct();
			Product product = productRepository.findById(idProduct)
					.orElseThrow(() -> new BusinessRulesException("Invalid product code: " + idProduct));

			ItenOrders itenOrders = new ItenOrders();
			itenOrders.setQuantity(dto.getQuantity());
			itenOrders.setOrders(orders);
			itenOrders.setProduct(product);
			return itenOrders;

		}).collect(Collectors.toList());
	}

	@Override
	public Optional<Orders> getCompletOrders(Integer id) {
		
		return ordersRepository.findByFetchItens(id);
	}

	@Override
	@Transactional
	public void updateStatus(Integer id, StatusOrders statusOrders) {
		ordersRepository
		.findById(id)
		.map(orders -> {
			orders.setStatus(statusOrders);
			return ordersRepository.save(orders);
		}).orElseThrow(() -> new OrderNotFoundException());
	}

}
