package org.XpenseTracks.services;

import org.XpenseTracks.data.repository.TransactionRepo;
import org.XpenseTracks.dtos.request.TransactionRequest;
import org.XpenseTracks.dtos.response.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class TransactionServicesImplTest {
    @Autowired
    private TransactionServicesImpl transactionServices;
    @Autowired
    private TransactionRepo transactionRepo;

    @BeforeEach
    public void setUp() {
        transactionRepo.deleteAll();
    }

    @Test
    public void testCreateTransaction() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setBudgetId("1");
        transactionRequest.setCategoryId("1");
        transactionRequest.setAmount(BigDecimal.valueOf(800));
        transactionRequest.setTransactionDate(LocalDateTime.now());
        transactionRequest.setDescription("Feeding");

        TransactionResponse transactionResponse = transactionServices.createTransaction(transactionRequest);
        assertNotNull(transactionResponse);
        assertNotNull(transactionResponse.getTransactionId());
        assertEquals("1", transactionResponse.getBudgetId());
        assertEquals("1", transactionResponse.getCategoryId());
        assertEquals(BigDecimal.valueOf(800), transactionResponse.getAmount());
        assertEquals("Feeding", transactionResponse.getDescription());
    }

    @Test
    public void testGetTransactionById() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setBudgetId("1");
        transactionRequest.setCategoryId("1");
        transactionRequest.setAmount(BigDecimal.valueOf(800));
        transactionRequest.setTransactionDate(LocalDateTime.now());
        transactionRequest.setDescription("Feeding");

        TransactionResponse savedTransaction = transactionServices.createTransaction(transactionRequest);
        TransactionResponse getTransactions = transactionServices.getTransactionById(savedTransaction.getTransactionId());

        assertNotNull(getTransactions);
        assertEquals(savedTransaction.getTransactionId(), getTransactions.getTransactionId());
        assertEquals(savedTransaction.getBudgetId(), getTransactions.getBudgetId());
        assertEquals(savedTransaction.getCategoryId(), getTransactions.getCategoryId());
        assertEquals(savedTransaction.getAmount(), getTransactions.getAmount());
        assertEquals(savedTransaction.getDescription(), getTransactions.getDescription());
    }

    @Test
    public void testUpdateTransaction() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setBudgetId("1");
        transactionRequest.setCategoryId("1");
        transactionRequest.setAmount(BigDecimal.valueOf(800));
        transactionRequest.setTransactionDate(LocalDateTime.now());
        transactionRequest.setDescription("Feeding");
        TransactionResponse savedTransaction = transactionServices.createTransaction(transactionRequest);

        TransactionRequest updateTransactionRequest = new TransactionRequest();
        updateTransactionRequest.setBudgetId("2");
        updateTransactionRequest.setCategoryId("2");
        updateTransactionRequest.setAmount(BigDecimal.valueOf(1000));
        updateTransactionRequest.setTransactionDate(LocalDateTime.now());
        updateTransactionRequest.setDescription("Transport");
        TransactionResponse updatedTransaction = transactionServices.updateTransaction(savedTransaction.getTransactionId(), updateTransactionRequest);

        assertNotNull(updatedTransaction);
        assertEquals(savedTransaction.getTransactionId(), updatedTransaction.getTransactionId());
        assertEquals("2", updatedTransaction.getBudgetId());
        assertEquals("2", updatedTransaction.getCategoryId());
        assertEquals(BigDecimal.valueOf(1000), updatedTransaction.getAmount());
        assertEquals("Transport", updatedTransaction.getDescription());
    }

    @Test
    public void testDeleteTransaction() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setBudgetId("1");
        transactionRequest.setCategoryId("1");
        transactionRequest.setAmount(BigDecimal.valueOf(800));
        transactionRequest.setTransactionDate(LocalDateTime.now());
        transactionRequest.setDescription("Feeding");
        TransactionResponse savedTransaction = transactionServices.createTransaction(transactionRequest);

        transactionServices.deleteTransaction(savedTransaction.getTransactionId());
        assertFalse(transactionRepo.existsById(savedTransaction.getTransactionId()));
    }

    @Test
    public void testGetAllTransactions() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setBudgetId("1");
        transactionRequest.setCategoryId("1");
        transactionRequest.setAmount(BigDecimal.valueOf(800));
        transactionRequest.setTransactionDate(LocalDateTime.now());
        transactionRequest.setDescription("Feeding");
        transactionServices.createTransaction(transactionRequest);

        TransactionRequest transactionRequest2 = new TransactionRequest();
        transactionRequest2.setBudgetId("2");
        transactionRequest2.setCategoryId("2");
        transactionRequest2.setAmount(BigDecimal.valueOf(1000));
        transactionRequest2.setTransactionDate(LocalDateTime.now());
        transactionRequest2.setDescription("Transport");
        transactionServices.createTransaction(transactionRequest2);

        List<TransactionResponse> transactions = transactionServices.getAllTransactions();
        assertEquals(2, transactions.size());
    }
}