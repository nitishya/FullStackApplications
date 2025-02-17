package com.bankapp.sbi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.sbi.model.Transaction;

@Repository // Marks this interface as a Spring Data JPA repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	// Custom query method to find transactions by accountId
	// This will return a list of transactions associated with a specific accountId
	List<Transaction> findByAccountId(Long accountId);
}
