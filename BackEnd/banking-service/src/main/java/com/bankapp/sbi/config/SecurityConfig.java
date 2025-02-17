package com.bankapp.sbi.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Security configuration class to set up CORS, CSRF protection, and session
 * management. Configures security settings for HTTP requests and JWT-based
 * stateless authentication.
 */
@Configuration
public class SecurityConfig {

	/**
	 * Configures the security filter chain. Sets up CORS, disables CSRF, and
	 * configures session management for stateless authentication.
	 * 
	 * @param http the HttpSecurity object to configure
	 * @return SecurityFilterChain the configured security filter chain
	 * @throws Exception if an error occurs during configuration
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Enable CORS (Cross-Origin Resource Sharing) to allow requests from the
		// Angular frontend
		return http.cors(cors -> cors.configurationSource(corsConfigurationSource())) // ✅ Enable CORS
				.csrf(csrf -> csrf.disable()) // ✅ Disable CSRF protection (needed for APIs)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // ✅
																												// Stateless
																												// session
																												// management
																												// (important
																												// for
																												// JWT)
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll() // ✅ Allow all requests for now (this can
																				// be customized later)
				).build();
	}

	/**
	 * Configures the CORS settings for the application. Allows specific origins and
	 * methods, and enables sending credentials (like JWT tokens) with requests.
	 * 
	 * @return CorsConfigurationSource the CORS configuration source
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		// Set allowed origins for CORS - only allow Angular frontend to make requests
		configuration.setAllowedOrigins(List.of("http://localhost:4200")); // ✅ Allow Angular app (running on
																			// localhost:4200)

		// Allow specific HTTP methods (GET, POST, PUT, DELETE, OPTIONS)
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

		// Specify allowed headers, such as Authorization (for sending JWT tokens) and
		// Content-Type
		configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

		// Allow sending credentials, such as JWT tokens, with cross-origin requests
		configuration.setAllowCredentials(true); // ✅ Allow credentials like JWT tokens

		// Register CORS configuration globally for all endpoints
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration to all paths

		return source; // Return the CORS configuration source
	}
}
