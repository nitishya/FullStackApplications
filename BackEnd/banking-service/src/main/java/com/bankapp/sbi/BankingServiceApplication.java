package com.bankapp.sbi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * This is the main entry point for the Spring Boot Banking Service Application.
 * It runs the application, initializing the Spring context and all necessary components.
 */
@SpringBootApplication
@EnableAsync  // Enables asynchronous processing
public class BankingServiceApplication {

    // Logger for this class, used for logging important application information
    private static final Logger logger = LoggerFactory.getLogger(BankingServiceApplication.class);

    /**
     * The main method, acting as the entry point of the Spring Boot application.
     * It calls the SpringApplication.run() to start up the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {

        // Log the startup process
        logger.info("Starting the Banking Service Application...");

        // Run the Spring Boot application. This will initialize the Spring context
        // and start up all the required services.
        SpringApplication.run(BankingServiceApplication.class, args);

        // Log after the application is successfully started
        logger.info("Banking Service Application started successfully!");
    }
}
