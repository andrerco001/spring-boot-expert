package ca.andre.spgboot.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ca.andre.spgboot.application.domain.entity.Customer;
import ca.andre.spgboot.application.domain.repository.CustomerRepository;

@SpringBootApplication
public class SalesApplication {
	@Bean
	public CommandLineRunner init(@Autowired CustomerRepository customerRepository) {
		return args -> {

			// Create
			System.out.println("Creating customers");
			customerRepository.save(new Customer("Andre"));
			customerRepository.save(new Customer("Lilys"));

			// Find All
			System.out.println("Find all customers");
			List<Customer> allCustomers = customerRepository.findAll();
			allCustomers.forEach(System.out::println);
			
			boolean existsCustomer = customerRepository.existsByName("Paul");
			System.out.println("Is there a customer named Paul? " + existsCustomer);
				

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
}
