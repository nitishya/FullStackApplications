package com.bankapp.sbi.config;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bankapp.sbi.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JwtAuthenticationFilter is a Spring Security filter that is used to
 * authenticate requests by extracting and validating the JWT token in the
 * Authorization header.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil; // JWT utility class to handle token operations

	/**
	 * Constructor that initializes JwtUtil.
	 * 
	 * @param jwtUtil JwtUtil class to extract and validate JWT tokens.
	 */
	public JwtAuthenticationFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	/**
	 * The core logic of the filter. It reads the Authorization header, validates
	 * the JWT token, and sets the authentication in the Spring Security context if
	 * the token is valid.
	 * 
	 * @param request     the HTTP request.
	 * @param response    the HTTP response.
	 * @param filterChain the filter chain.
	 * @throws ServletException if an error occurs during filter processing.
	 * @throws IOException      if an input/output error occurs.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Extract the 'Authorization' header from the request
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);

		// Check if the Authorization header is present and starts with "Bearer "
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7); // Extract the JWT token from the header

			// Extract the username from the token and validate the token
			String username = jwtUtil.extractUsername(token);

			// If the token is valid, set the authentication context
			if (username != null && jwtUtil.validateToken(token, username)) {
				// Create an authentication token for the user (no password or authorities
				// needed for now)
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
						null, null);

				// Set the authentication token in the Security Context
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		// Continue with the next filter in the chain
		filterChain.doFilter(request, response);
	}
}
