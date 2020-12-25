package ca.andre.spgboot.application.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.andre.spgboot.application.domain.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> 
{

	List<Customer> findByNameLike(String name);
	
	
	
	

}
