package com.bankapp.sbi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.sbi.model.Account;
import com.bankapp.sbi.service.AccountService;

@RestController
@RequestMapping("/api/accounts") // Base URL for all account-related endpoints
public class AccountController {

	private final AccountService accountService;

	// Constructor Injection of AccountService to handle account-related operations
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Create a new bank account for a user.
	 * 
	 * @param userId - the ID of the user to associate the account with.
	 * @return ResponseEntity containing the created Account object.
	 */
	@PostMapping("/create/{userId}") // Create a new account with a specific user
	public ResponseEntity<Account> createAccount(@PathVariable("userId") Long userId) {
		// Call service method to create an account for the given user ID.
		return ResponseEntity.ok(accountService.createAccount(userId));
	}

	/**
	 * Get account details by account ID.
	 * 
	 * @param accountId - the account ID for which details are needed.
	 * @return ResponseEntity containing the Account details.
	 */
	@GetMapping("/{accountId}") // Fetch account details using account ID
	public ResponseEntity<Account> getAccountById(@PathVariable("accountId") Long accountId) {
		// Fetch the account by ID from the service layer and return it.
		return ResponseEntity.ok(accountService.getAccountById(accountId));
	}

	/**
	 * Get all accounts associated with a specific user.
	 * 
	 * @param userId - the user ID whose accounts need to be fetched.
	 * @return ResponseEntity containing a list of accounts for the user.
	 */
	@GetMapping("/user/{userId}") // Fetch all accounts for a given user
	public ResponseEntity<List<Account>> getAccountsByUser(@PathVariable("userId") Long userId) {
		// Call service to fetch all accounts linked with the user.
		return ResponseEntity.ok(accountService.getAccountsByUserId(userId));
	}

	/**
	 * Deposit money into a specific account.
	 * 
	 * @param accountId - the account ID where money will be deposited.
	 * @param request   - a map containing the deposit amount.
	 * @return ResponseEntity containing the updated Account after deposit.
	 */
	@PostMapping("/{accountId}/deposit") // Deposit money into an account
	public ResponseEntity<Account> deposit(@PathVariable("accountId") Long accountId,
			@RequestBody Map<String, Double> request) {
		// Call service to deposit the specified amount into the account.
		return ResponseEntity.ok(accountService.deposit(accountId, request.get("amount")));
	}

	/**
	 * Withdraw money from a specific account.
	 * 
	 * @param accountId - the account ID from which money will be withdrawn.
	 * @param request   - a map containing the withdrawal amount.
	 * @return ResponseEntity containing the updated Account after withdrawal.
	 */
	@PostMapping("/{accountId}/withdraw") // Withdraw money from an account
	public ResponseEntity<Account> withdraw(@PathVariable("accountId") Long accountId,
			@RequestBody Map<String, Double> request) {
		// Call service to withdraw the specified amount from the account.
		return ResponseEntity.ok(accountService.withdraw(accountId, request.get("amount")));
	}
}
