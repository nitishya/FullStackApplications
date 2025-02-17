package com.bankapp.sbi.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors; // Java 8 Stream API for optimization

import org.springframework.stereotype.Service;

import com.bankapp.sbi.exception.BadRequestException;
import com.bankapp.sbi.exception.ResourceNotFoundException;
import com.bankapp.sbi.model.Account;
import com.bankapp.sbi.model.User;
import com.bankapp.sbi.repository.AccountRepository;
import com.bankapp.sbi.repository.UserRepository;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	private final UserRepository userRepository;

	// Constructor injection for dependencies (AccountRepository, UserRepository)
	public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
	}

	/**
	 * Create a new bank account for a user.
	 * 
	 * @param userId - ID of the user for whom the account is to be created.
	 * @return Account - the created account with the assigned account number.
	 */
	public Account createAccount(Long userId) {
		// Fetch the user from the database, throw exception if not found.
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

		// Generate a unique account number for the user.
		String accountNumber = generateUniqueAccountNumber();
		// Create and save the new account for the user.
		Account account = new Account(null, accountNumber, 0.0, user);
		return accountRepository.save(account);
	}

	/**
	 * Fetch an account by its ID.
	 * 
	 * @param accountId - ID of the account to fetch.
	 * @return Account - the fetched account.
	 */
	public Account getAccountById(Long accountId) {
		// Find account by ID, throw exception if not found.
		return accountRepository.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + accountId));
	}

	/**
	 * Fetch all accounts associated with a given user ID.
	 * 
	 * @param userId - ID of the user whose accounts are to be fetched.
	 * @return List<Account> - list of accounts belonging to the user.
	 */
	public List<Account> getAccountsByUserId(Long userId) {
		// Using Java 8 Stream API to filter accounts by user ID (efficient).
		return accountRepository.findAll().stream().filter(account -> account.getUser().getId().equals(userId))
				.collect(Collectors.toList()); // Collect into a list.
	}

	/**
	 * Deposit a specified amount into a user's account.
	 * 
	 * @param accountId - ID of the account to deposit into.
	 * @param amount    - the amount to deposit.
	 * @return Account - the updated account after deposit.
	 */
	public Account deposit(Long accountId, Double amount) {
		if (amount <= 0) {
			throw new BadRequestException("Deposit amount must be greater than 0.");
		}

		Account account = getAccountById(accountId);
		// Add the deposit amount to the account's balance.
		account.setBalance(account.getBalance() + amount);
		return accountRepository.save(account); // Save the updated account.
	}

	/**
	 * Withdraw a specified amount from a user's account.
	 * 
	 * @param accountId - ID of the account to withdraw from.
	 * @param amount    - the amount to withdraw.
	 * @return Account - the updated account after withdrawal.
	 */
	public Account withdraw(Long accountId, Double amount) {
		if (amount <= 0) {
			throw new BadRequestException("Withdrawal amount must be greater than 0.");
		}

		Account account = getAccountById(accountId);
		if (account.getBalance() < amount) {
			throw new BadRequestException("Insufficient balance for withdrawal.");
		}

		// Deduct the withdrawal amount from the account's balance.
		account.setBalance(account.getBalance() - amount);
		return accountRepository.save(account); // Save the updated account.
	}

	/**
	 * Generate a unique account number for a new account.
	 * 
	 * @return String - the generated account number.
	 */
	private String generateUniqueAccountNumber() {
		// Generating a random 6-digit number and prefixing it with "ACC" to form
		// account number.
		return "ACC" + (100000 + new Random().nextInt(900000));
	}
}
