package ca.andre.spgboot.application.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.andre.spgboot.application.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);

}
