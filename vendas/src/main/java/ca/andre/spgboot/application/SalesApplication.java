package ca.andre.spgboot.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ca.andre.spgboot.application.domain.entity.Customer;
import ca.andre.spgboot.application.domain.repository.CustomerRepository;

@SpringBootApplication
public class SalesApplication 
{
	@Bean
	public CommandLineRunner  commandLineRunner(@Autowired CustomerRepository customerRepository) {
		
		return args -> {
			Customer customer = new Customer(null, "Andre");
			customerRepository.save(customer);
		};
	}
	
	public static void main(String[] args) 
	{
		SpringApplication.run(SalesApplication.class, args);
	}
}
