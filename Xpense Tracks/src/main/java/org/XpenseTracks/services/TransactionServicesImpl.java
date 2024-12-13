package org.XpenseTracks.services;

import org.XpenseTracks.data.model.Transaction;
import org.XpenseTracks.data.repository.TransactionRepo;
import org.XpenseTracks.dtos.request.TransactionRequest;
import org.XpenseTracks.dtos.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServicesImpl implements TransactionServices {

    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        if(transactionRequest.getBudgetId() == null || transactionRequest.getBudgetId().isEmpty()){
            throw new IllegalArgumentException("Invalid budget id");
        }
        if(transactionRequest.getCategoryId() == null || transactionRequest.getCategoryId().isEmpty()){
            throw new IllegalArgumentException("Invalid category id");
        }
        if(transactionRequest.getAmount() == null || transactionRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Invalid amount, Amount must be greater than zero");
        }
        if(transactionRequest.getTransactionDate() == null){
            throw new IllegalArgumentException("Invalid transaction date");
        }
        Transaction transaction = new Transaction();
        transaction.setBudgetId(transactionRequest.getBudgetId());
        transaction.setCategoryId(transactionRequest.getCategoryId());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setTransactionDate(transactionRequest.getTransactionDate());
        transaction.setDescription(transactionRequest.getDescription());
        Transaction savedTransaction = transactionRepo.save(transaction);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionId(savedTransaction.getTransactionId());
        transactionResponse.setBudgetId(savedTransaction.getBudgetId());
        transactionResponse.setCategoryId(savedTransaction.getCategoryId());
        transactionResponse.setAmount(savedTransaction.getAmount());
        transactionResponse.setTransactionDate(savedTransaction.getTransactionDate());
        transactionResponse.setDescription(savedTransaction.getDescription());
        return transactionResponse;
    }

    @Override
    public TransactionResponse getTransactionById(String transactionId) {
        Optional<Transaction> getTransactionId = transactionRepo.findById(transactionId);
        if(getTransactionId.isPresent()){
            Transaction transaction = getTransactionId.get();
            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.setTransactionId(transaction.getTransactionId());
            transactionResponse.setBudgetId(transaction.getBudgetId());
            transactionResponse.setCategoryId(transaction.getCategoryId());
            transactionResponse.setAmount(transaction.getAmount());
            transactionResponse.setTransactionDate(transaction.getTransactionDate());
            transactionResponse.setDescription(transaction.getDescription());
            return transactionResponse;
        } else {
            throw new IllegalArgumentException("Transaction not found");
        }
    }

    @Override
    public TransactionResponse updateTransaction(String transactionId, TransactionRequest transactionRequest) {
        Optional<Transaction> getTransactionId = transactionRepo.findById(transactionId);
        if(getTransactionId.isPresent()){
            Transaction transaction = getTransactionId.get();
            transaction.setBudgetId(transactionRequest.getBudgetId());
            transaction.setCategoryId(transactionRequest.getCategoryId());
            transaction.setAmount(transactionRequest.getAmount());
            transaction.setTransactionDate(transactionRequest.getTransactionDate());
            transaction.setDescription(transactionRequest.getDescription());
            Transaction savedTransaction = transactionRepo.save(transaction);

            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.setTransactionId(savedTransaction.getTransactionId());
            transactionResponse.setBudgetId(savedTransaction.getBudgetId());
            transactionResponse.setCategoryId(savedTransaction.getCategoryId());
            transactionResponse.setAmount(savedTransaction.getAmount());
            transactionResponse.setTransactionDate(savedTransaction.getTransactionDate());
            transactionResponse.setDescription(savedTransaction.getDescription());
            return transactionResponse;
        } else{
            throw new IllegalArgumentException("Transaction not found");
        }
    }

    @Override
    public void deleteTransaction(String transactionId) {
        if (transactionRepo.existsById(transactionId)) {
            transactionRepo.deleteById(transactionId);
        }else {
            throw new IllegalArgumentException("Transaction not found");
        }
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        List<Transaction> transactions = transactionRepo.findAll();
        return transactions.stream().map(transaction -> {
            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.setTransactionId(transaction.getTransactionId());
            transactionResponse.setBudgetId(transaction.getBudgetId());
            transactionResponse.setCategoryId(transaction.getCategoryId());
            transactionResponse.setAmount(transaction.getAmount());
            transactionResponse.setTransactionDate(transaction.getTransactionDate());
            transactionResponse.setDescription(transaction.getDescription());
            return transactionResponse;
        }).collect(Collectors.toList());
    }
}
