package com.bankapp.sbi.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.sbi.model.User;
import com.bankapp.sbi.service.UserService;
import com.bankapp.sbi.util.JwtUtil;

/**
 * Controller class to handle authentication-related endpoints. Provides API
 * methods for user registration and login.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UserService userService;
	private final JwtUtil jwtUtil;

	// Constructor injection for UserService and JwtUtil
	public AuthController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	/**
	 * API endpoint to register a new user. Receives user details from the request
	 * body and returns the created user.
	 * 
	 * @param request Contains username, email, and password
	 * @return ResponseEntity with created user
	 */
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
		// Register a new user by passing the received username, email, and password
		User user = userService.registerUser(request.get("username"), request.get("email"), request.get("password"));

		// Return a successful response with the created user
		return ResponseEntity.ok(user);
	}

	/**
	 * API endpoint to login a user. Authenticates the user by verifying the
	 * provided username and password. Generates a JWT token upon successful
	 * authentication.
	 * 
	 * @param request Contains username and password
	 * @return ResponseEntity with authentication result (token and user info)
	 */
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody(required = false) Map<String, String> request) {
		// Check if the request body contains both username and password
		if (request == null || !request.containsKey("username") || !request.containsKey("password")) {
			// Return a bad request response if either username or password is missing
			return ResponseEntity.badRequest().body("Error: Username and password are required.");
		}

		// Use the userService to authenticate the user based on the provided username
		// and password
		Optional<User> user = userService.authenticateUser(request.get("username"), request.get("password"));

		// Check if the user was found and the credentials match
		if (user.isPresent()) {
			// Generate a JWT token for the authenticated user
			String token = jwtUtil.generateToken(user.get().getUsername());

			// Prepare the response map containing the token, userId, and username
			Map<String, Object> response = new HashMap<>();
			response.put("token", token);
			response.put("userId", user.get().getId()); // Add userId to the response
			response.put("username", user.get().getUsername());

			// Return a successful response with the token and user details
			return ResponseEntity.ok(response);
		} else {
			// Return an unauthorized response if authentication fails
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
		}
	}
}
