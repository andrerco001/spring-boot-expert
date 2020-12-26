package ca.andre.spgboot.application;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ca.andre.spgboot.application.domain.entity.Customer;
import ca.andre.spgboot.application.domain.entity.Orders;
import ca.andre.spgboot.application.domain.repository.CustomerRepository;
import ca.andre.spgboot.application.domain.repository.OrdersRepository;

@SpringBootApplication
public class SalesApplication {
	@Bean
	public CommandLineRunner init( @Autowired CustomerRepository customerRepository, 
								   @Autowired OrdersRepository ordersRepository) {
		return args -> {

			// Create
			System.out.println("Creating customers");
			Customer customer1 = new Customer("Andre");
			customerRepository.save(customer1);
			
			Orders o1 = new Orders();
			o1.setCustomer(customer1);
			o1.setOrderDate(LocalDate.now());
			o1.setTotal(BigDecimal.valueOf(100.00));
			
			ordersRepository.save(o1);
			
//			Customer c1 = customerRepository.findCustomerFetchOrders(customer1.getId());
//			System.out.println(c1);
//			System.out.println(c1.getOrders());
			
			ordersRepository.findByCustomer(customer1).forEach(System.out::println);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
}
