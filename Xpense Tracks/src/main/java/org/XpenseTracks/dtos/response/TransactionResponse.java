package org.XpenseTracks.dtos.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private String transactionId;
    private String budgetId;
    private String categoryId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private String description;
}
