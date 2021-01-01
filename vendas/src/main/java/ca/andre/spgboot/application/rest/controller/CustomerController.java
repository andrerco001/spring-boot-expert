package ca.andre.spgboot.application.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ca.andre.spgboot.application.domain.entity.Customer;
import ca.andre.spgboot.application.domain.repository.CustomerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/customer")
@Api("Api Customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("{id}")
	@ApiOperation("Get customer by id.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Customer found!"), 
		@ApiResponse(code = 404, message = "Client not found for the given id!")
	})
	public Customer getCustomerById(@PathVariable("id") @ApiParam("Customer id") Integer id) {

		return customerRepository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Save a new customer.")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Customer saved successfully!"), 
		@ApiResponse(code = 400, message = "Validation error!")
	})
	public Customer save(@RequestBody @Valid Customer customer) {
		return customerRepository.save(customer);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		
		customerRepository
		.findById(id)
		.map(customer -> {
			customerRepository.delete(customer);
			return customer;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!"));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Integer id, @RequestBody @Valid Customer customer) {
		 customerRepository
		 .findById(id)
		 .map(existCustomer -> {
			customer.setId(existCustomer.getId());
			customerRepository.save(customer);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!"));
	}

	@GetMapping
	public List<Customer> find(Customer filter) {

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Customer> example = Example.of(filter, matcher);
		return customerRepository.findAll(example);
	}

}
