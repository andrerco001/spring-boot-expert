package ca.andre.spgboot.application.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.andre.spgboot.application.domain.entity.Customer;
import ca.andre.spgboot.application.domain.repository.CustomerRepository;

@Controller
public class CustomerController 
{
	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@GetMapping("/api/customer/{id}")
	@ResponseBody
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) 
	{
		Optional<Customer> customer = customerRepository.findById(id);
		
		if(customer.isPresent()) 
		{
			return ResponseEntity.ok(customer.get());
		} else {
			
			return ResponseEntity.notFound().build();
		}
	}
	
}
