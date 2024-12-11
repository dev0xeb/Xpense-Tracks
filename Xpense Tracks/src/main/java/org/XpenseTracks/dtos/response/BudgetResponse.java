package org.XpenseTracks.dtos.response;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class BudgetResponse {
    private String budgetId;
    private BigDecimal budgetAmount;
}
