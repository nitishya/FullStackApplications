package com.bankapp.sbi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.sbi.model.Account;

@Repository // Indicates this is a Spring Data repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	/**
	 * Find an account by its unique account number.
	 * 
	 * @param accountNumber - the account number to search for.
	 * @return Optional<Account> - returns an Optional containing the Account if
	 *         found.
	 */
	Optional<Account> findByAccountNumber(String accountNumber);
}
