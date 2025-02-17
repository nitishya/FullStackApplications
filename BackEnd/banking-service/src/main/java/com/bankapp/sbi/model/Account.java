package com.bankapp.sbi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Account entity represents a user's bank account in the application. It
 * contains details about the account number, balance, and the associated user.
 */
@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented ID for each account
	private Long id;

	@Column(unique = true, nullable = false) // Account number should be unique and non-nullable
	private String accountNumber;

	@Column(nullable = false) // Account balance should always be present and non-null
	private Double balance;

	@ManyToOne // An account is associated with a single user (Many accounts can belong to one
				// user)
	@JoinColumn(name = "user_id", nullable = false) // Foreign key for the associated user
	private User user; // User associated with the account

}
