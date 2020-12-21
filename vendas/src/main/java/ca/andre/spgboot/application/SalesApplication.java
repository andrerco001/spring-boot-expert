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

			List<Customer> allCustomers = customerRepository.findAll();
			allCustomers.forEach(System.out::println);

			// Read
			System.out.println("Find by name");
			customerRepository.findByName("Lil").forEach(System.out::println);

			// Update
			System.out.println("Update");
			allCustomers.forEach(c -> {
				c.setName(c.getName() + " atualizado");
				customerRepository.update(c);
			});

			// Fid all
			System.out.println("Find all customers");
			allCustomers = customerRepository.findAll();
			allCustomers.forEach(System.out::println);

			// Delete
			System.out.println("Deleting customers");
			customerRepository.findAll().forEach(c -> {
				customerRepository.delete(c.getId());
			});

			allCustomers = customerRepository.findAll();
			if (allCustomers.isEmpty()) {
				System.out.println("Customers not found!");

			} else {
				allCustomers.forEach(System.out::println);
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
}
