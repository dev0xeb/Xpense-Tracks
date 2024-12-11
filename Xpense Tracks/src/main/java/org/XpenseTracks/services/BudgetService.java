package org.XpenseTracks.services;

import org.XpenseTracks.dtos.request.BudgetRequest;
import org.XpenseTracks.dtos.response.BudgetResponse;

public interface BudgetService {
    BudgetResponse createBudget(BudgetRequest budgetRequest);
    BudgetResponse getBudgetById(String budgetId);
    BudgetResponse updateBudget(String budgetId, BudgetRequest budgetRequest);
    void deleteBudget(String budgetId);
}
