package org.XpenseTracks.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Document
public class Transaction {
    @Id
    private String transactionId;
    private String budgetId;
    private String categoryId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private String description;

}
