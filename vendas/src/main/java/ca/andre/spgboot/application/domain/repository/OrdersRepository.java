package ca.andre.spgboot.application.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.andre.spgboot.application.domain.entity.Customer;
import ca.andre.spgboot.application.domain.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	
	List<Orders> findByCustomer(Customer customer);

}
