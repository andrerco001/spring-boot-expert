package ca.andre.spgboot.application.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.andre.spgboot.application.domain.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
