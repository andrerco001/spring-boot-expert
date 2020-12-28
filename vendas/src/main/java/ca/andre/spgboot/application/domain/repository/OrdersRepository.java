package ca.andre.spgboot.application.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ca.andre.spgboot.application.domain.entity.Customer;
import ca.andre.spgboot.application.domain.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	
	List<Orders> findByCustomer(Customer customer);
	
	@Query("select o from Orders o left join fetch o.itenOrders where o.id = :id")
	Optional<Orders> findByFetchItens(@Param("id") Integer id);

}
