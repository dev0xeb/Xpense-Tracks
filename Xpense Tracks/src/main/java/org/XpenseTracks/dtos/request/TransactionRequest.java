package org.XpenseTracks.dtos.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionRequest {
    private String budgetId;
    private String categoryId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private String description;
}
