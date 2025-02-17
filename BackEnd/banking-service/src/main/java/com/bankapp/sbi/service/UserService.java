package com.bankapp.sbi.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bankapp.sbi.model.User;
import com.bankapp.sbi.repository.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class); // Logger for logging events

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	// Constructor to inject dependencies (UserRepository and BCryptPasswordEncoder)
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder(); // BCryptPasswordEncoder to handle password encryption
	}

	/**
	 * Registers a new user with the provided username, email, and password. The
	 * password is encoded using BCrypt before saving the user.
	 * 
	 * @param username The username of the user
	 * @param email    The email address of the user
	 * @param password The password of the user (plaintext)
	 * @return The saved User object
	 */
	public User registerUser(String username, String email, String password) {
		logger.info("Attempting to register user with username: {}", username); // Log the attempt

		// Encrypt the password before saving it
		String encodedPassword = passwordEncoder.encode(password);
		User user = new User(null, username, encodedPassword, email); // Create a new User object

		// Save the user to the repository and return the saved user object
		User savedUser = userRepository.save(user);
		logger.info("User registered successfully with username: {}", username); // Log successful registration

		return savedUser;
	}

	/**
	 * Authenticates a user based on the provided username and password. The
	 * password entered by the user is compared with the stored encrypted password.
	 * 
	 * @param username The username of the user
	 * @param password The password entered by the user (plaintext)
	 * @return An Optional containing the User if authentication is successful, or
	 *         an empty Optional if failed
	 */
	public Optional<User> authenticateUser(String username, String password) {
		logger.info("Attempting to authenticate user with username: {}", username); // Log the attempt

		// Retrieve the user by username from the repository
		Optional<User> user = userRepository.findByUsername(username);

		// If user exists and the password matches, return the user
		if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
			logger.info("User authenticated successfully: {}", username); // Log successful authentication
			return user;
		}

		// If authentication fails, log the failure and return empty Optional
		logger.warn("Authentication failed for username: {}", username); // Log failed authentication
		return Optional.empty();
	}
}
