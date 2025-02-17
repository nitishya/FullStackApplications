package com.bankapp.sbi.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapp.sbi.model.Transaction;
import com.bankapp.sbi.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("accountId") Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccount(accountId));
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<Transaction> deposit(@PathVariable("accountId") Long accountId,
                                               @RequestBody Map<String, Double> request) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(transactionService.deposit(accountId, request.get("amount")).get()); // Get the result
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<Transaction> withdraw(@PathVariable("accountId") Long accountId,
                                                @RequestBody Map<String, Double> request) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(transactionService.withdraw(accountId, request.get("amount")).get()); // Get the result
    }

    @PostMapping("/{fromAccountId}/transfer/{toAccountId}")
    public ResponseEntity<Transaction> transfer(@PathVariable("fromAccountId") Long fromAccountId,
                                                @PathVariable("toAccountId") Long toAccountId,
                                                @RequestBody Map<String, Double> request) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(transactionService.transfer(fromAccountId, toAccountId, request.get("amount")).get()); // Get the result
    }
}
