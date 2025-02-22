package com.bankapp.sbi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a User in the banking application.
 * This class is mapped to the 'users' table in the database.
 */
@Entity
@Table(name = "users") // Maps this class to the 'users' table in the database
@Getter
@Setter
@NoArgsConstructor  // Generates a no-args constructor
@AllArgsConstructor // Generates a constructor with all arguments
public class User {

    // The primary key of the User entity (auto-generated by the database)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID is auto-generated and will be unique for each user
    private Long id;

    // Username field (unique and cannot be null)
    @Column(unique = true, nullable = false)
    private String username;

    // Password field (cannot be null)
    @Column(nullable = false)
    private String password;

    // Email field (cannot be null)
    @Column(nullable = false)
    private String email;

   
}
