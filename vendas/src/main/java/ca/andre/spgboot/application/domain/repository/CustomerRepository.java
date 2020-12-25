package ca.andre.spgboot.application.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.andre.spgboot.application.domain.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> 
{

	@Query(value = "select * from customer c where c.name like %:name%", nativeQuery = true)
	List<Customer> findByNameCustomer(@Param("name") String name);
	
	@Query(value = "delete from customer c where c.name = :name")
	@Modifying
	void deleteByName(@Param("name") String name);
	
	boolean existsByName(String name);
	

}
