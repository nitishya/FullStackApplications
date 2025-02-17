package com.bankapp.sbi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.sbi.model.User;

/**
 * Repository interface for accessing User data in the database. Extends
 * JpaRepository to provide built-in CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds a user by their username.
	 * 
	 * @param username The username of the user
	 * @return An Optional containing the User if found, or an empty Optional if not
	 */
	Optional<User> findByUsername(String username);
}
