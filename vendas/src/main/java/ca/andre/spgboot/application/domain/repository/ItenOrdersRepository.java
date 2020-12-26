package ca.andre.spgboot.application.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.andre.spgboot.application.domain.entity.ItenOrders;

public interface ItenOrdersRepository extends JpaRepository<ItenOrders, Integer> {

}
