package com.bankapp.sbi.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankapp.sbi.exception.BadRequestException;
import com.bankapp.sbi.exception.ResourceNotFoundException;
import com.bankapp.sbi.model.Account;
import com.bankapp.sbi.model.Transaction;
import com.bankapp.sbi.model.Transaction.TransactionType;
import com.bankapp.sbi.repository.AccountRepository;
import com.bankapp.sbi.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class); // Logger

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    // Fetch All Transactions for an Account
    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    // Deposit Money
    @Async
    @Transactional
    public CompletableFuture<Transaction> deposit(Long accountId, Double amount) {
        if (amount <= 0) {
            throw new BadRequestException("Deposit amount must be greater than 0.");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + accountId));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAmount(amount);
        transaction.setAccount(account);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return CompletableFuture.completedFuture(savedTransaction);
    }

    // Withdraw Money
    @Async
    @Transactional
    public CompletableFuture<Transaction> withdraw(Long accountId, Double amount) {
        if (amount <= 0) {
            throw new BadRequestException("Withdrawal amount must be greater than 0.");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + accountId));

        // Log warning if withdrawal exceeds current balance
        if (account.getBalance() < amount) {
            logger.warn("Warning: Withdrawal amount ({}) exceeds current balance ({}) for account ID: {}.",
                    amount, account.getBalance(), accountId);
            throw new BadRequestException("Insufficient balance.");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.WITHDRAW);
        transaction.setAmount(amount);
        transaction.setAccount(account);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return CompletableFuture.completedFuture(savedTransaction);
    }

    // Transfer Money Between Accounts
    @Async
    @Transactional
    public CompletableFuture<Transaction> transfer(Long fromAccountId, Long toAccountId, Double amount) {
        if (amount <= 0) {
            throw new BadRequestException("Transfer amount must be greater than 0.");
        }

        if (fromAccountId.equals(toAccountId)) {
            throw new BadRequestException("Cannot transfer to the same account.");
        }

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Sender account not found."));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Receiver account not found."));

        if (fromAccount.getBalance() < amount) {
            throw new BadRequestException("Insufficient balance.");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Create transaction for the sender
        Transaction senderTransaction = new Transaction();
        senderTransaction.setType(TransactionType.TRANSFER);
        senderTransaction.setAmount(amount);
        senderTransaction.setAccount(fromAccount);
        senderTransaction.setToAccount(toAccount);

        // Create transaction for the receiver
        Transaction receiverTransaction = new Transaction();
        receiverTransaction.setType(TransactionType.RECEIVED);
        receiverTransaction.setAmount(amount);
        receiverTransaction.setAccount(toAccount);
        receiverTransaction.setToAccount(fromAccount);

        transactionRepository.save(senderTransaction);
        transactionRepository.save(receiverTransaction);

        return CompletableFuture.completedFuture(senderTransaction);
    }
}
