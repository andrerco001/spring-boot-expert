package ca.andre.spgboot.application.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.andre.spgboot.application.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
