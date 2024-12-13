package org.XpenseTracks.services;

import org.XpenseTracks.dtos.request.TransactionRequest;
import org.XpenseTracks.dtos.response.TransactionResponse;

import java.util.List;

public interface TransactionServices {
    TransactionResponse createTransaction(TransactionRequest transactionRequest);
    TransactionResponse getTransactionById(String transactionId);
    TransactionResponse updateTransaction(String transactionId, TransactionRequest transactionRequest);
    void deleteTransaction(String transactionId);
    List<TransactionResponse> getAllTransactions();
}
