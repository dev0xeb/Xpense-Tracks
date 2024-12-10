package org.XpenseTracks.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@Data
@Document
public class Budget {
    private String budgetId;
    private String userId;
    private BigDecimal budgetAmount;
}
