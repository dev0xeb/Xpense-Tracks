package org.XpenseTracks.controllers;

import org.XpenseTracks.dtos.request.TransactionRequest;
import org.XpenseTracks.dtos.response.TransactionResponse;
import org.XpenseTracks.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionServices transactionServices;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = transactionServices.createTransaction(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponse);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransactionsById(@PathVariable String transactionId) {
        TransactionResponse transactionResponse = transactionServices.getTransactionById(transactionId);
        if (transactionResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(transactionResponse);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable String transactionId, @RequestBody TransactionRequest transactionRequest) {
        TransactionResponse transactionResponse = transactionServices.updateTransaction(transactionId, transactionRequest);
        return ResponseEntity.ok(transactionResponse);
    }

    @DeleteMapping("/{transactioId}")
    public ResponseEntity<TransactionResponse> deleteTransaction(@PathVariable String transactioId) {
        transactionServices.deleteTransaction(transactioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        List<TransactionResponse> transactionResponses = transactionServices.getAllTransactions();
        return ResponseEntity.ok(transactionResponses);
    }
}
