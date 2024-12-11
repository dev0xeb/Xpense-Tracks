package org.XpenseTracks.dtos.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class BudgetRequest {
    private String userId;
    private BigDecimal budgetAmount;
}
