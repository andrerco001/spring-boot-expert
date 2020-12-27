package ca.andre.spgboot.application.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.andre.spgboot.application.domain.entity.Customer;
import ca.andre.spgboot.application.domain.repository.CustomerRepository;

@Controller
public class CustomerController 
{
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) 
	{
		this.customerRepository = customerRepository;
	}

	@GetMapping("/api/customer/{id}")
	@ResponseBody
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) 
	{
		Optional<Customer> customer = customerRepository.findById(id);

		if (customer.isPresent()) {
			return ResponseEntity.ok(customer.get());
		} else {

			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/api/customer")
	@ResponseBody
	public ResponseEntity<Customer> save(@RequestBody Customer customer) 
	{
		Customer savedCustomer = customerRepository.save(customer);

		return ResponseEntity.ok(savedCustomer);
	}

	@DeleteMapping("/api/customer/{id}")
	@ResponseBody
	public ResponseEntity<Customer> delete(@PathVariable("id") Integer id) 
	{
		Optional<Customer> customer = customerRepository.findById(id);

		if (customer.isPresent()) {
			customerRepository.delete(customer.get());
			
			return ResponseEntity.noContent().build();
		} else {

			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/api/customer/{id}")
	@ResponseBody
	public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Customer customer) 
	{
		return customerRepository
				.findById(id)
				.map(existCustomer -> {
			customer.setId(existCustomer.getId());
			customerRepository.save(customer);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
