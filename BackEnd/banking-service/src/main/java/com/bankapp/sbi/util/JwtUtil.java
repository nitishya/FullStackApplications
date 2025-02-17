package com.bankapp.sbi.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JwtUtil is a utility class used for generating and validating JWT tokens in
 * the application. It handles token creation, validation, username extraction,
 * and checking token expiration.
 */
@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secretKey; // Secret key for signing the JWT (configured in application.properties)

	@Value("${jwt.expiration}")
	private long expirationTime; // Expiration time for the token in milliseconds

	/**
	 * Generates a JWT token for the given username. The token is signed with a
	 * secret key and includes the username as the subject, along with issue and
	 * expiration dates.
	 *
	 * @param username the username to include in the token.
	 * @return the generated JWT token.
	 */
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username) // Set the username as the subject (payload) of the token
				.setIssuedAt(new Date()) // Set the token issue time to the current time
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Set the token expiration time
				.signWith(SignatureAlgorithm.HS256, secretKey) // Sign the token using HMAC with SHA-256 algorithm
				.compact(); // Return the final token as a compact string
	}

	/**
	 * Validates the JWT token by checking the username and the token's expiration.
	 *
	 * @param token    the JWT token to validate.
	 * @param username the username to verify in the token.
	 * @return true if the token is valid (username matches and the token is not
	 *         expired), false otherwise.
	 */
	public boolean validateToken(String token, String username) {
		return (username.equals(extractUsername(token)) && !isTokenExpired(token)); // Check username and expiration
	}

	/**
	 * Extracts the username (subject) from the JWT token.
	 *
	 * @param token the JWT token from which to extract the username.
	 * @return the username extracted from the token.
	 */
	public String extractUsername(String token) {
		return Jwts.parser() // Parse the JWT token
				.setSigningKey(secretKey) // Set the secret key for signature verification
				.parseClaimsJws(token) // Parse the JWT and get the claims (payload)
				.getBody() // Get the body (claims) from the parsed JWT
				.getSubject(); // Extract the subject (username) from the claims
	}

	/**
	 * Checks if the token has expired.
	 *
	 * @param token the JWT token to check.
	 * @return true if the token has expired, false otherwise.
	 */
	public boolean isTokenExpired(String token) {
		return Jwts.parser() // Parse the JWT token
				.setSigningKey(secretKey) // Set the secret key for signature verification
				.parseClaimsJws(token) // Parse the JWT and get the claims
				.getBody() // Get the body (claims) from the parsed JWT
				.getExpiration() // Get the expiration date of the token
				.before(new Date()); // Check if the token's expiration date is before the current date
	}
}
