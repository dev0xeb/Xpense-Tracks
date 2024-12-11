package org.XpenseTracks.services;

import org.XpenseTracks.data.model.Budget;
import org.XpenseTracks.data.repository.BudgetRepo;
import org.XpenseTracks.dtos.request.BudgetRequest;
import org.XpenseTracks.dtos.response.BudgetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepo budgetRepo;

    @Override
    public BudgetResponse createBudget(BudgetRequest budgetRequest) {
        if(budgetRequest.getUserId() == null || budgetRequest.getUserId().isEmpty()){
            throw new IllegalArgumentException("Invalid user id");
        }
        if(budgetRequest.getBudgetAmount() == null || budgetRequest.getBudgetAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new NullPointerException("Budget amount must be greater than zero");
        }
        Optional<Budget> getBudgetId = budgetRepo.findByUserId(budgetRequest.getUserId());
        if (getBudgetId.isPresent()) {
            throw new IllegalArgumentException("Budget already exists");
        }
        Budget budget = new Budget();
        budget.setUserId(budgetRequest.getUserId());
        budget.setBudgetAmount(budgetRequest.getBudgetAmount() != null ? budgetRequest.getBudgetAmount() : BigDecimal.ZERO);
        Budget savedBudget = budgetRepo.save(budget);

        BudgetResponse budgetResponse = new BudgetResponse();
        budgetResponse.setBudgetId(savedBudget.getBudgetId());
        budgetResponse.setBudgetAmount(savedBudget.getBudgetAmount());
        return budgetResponse;
    }

    @Override
    public BudgetResponse getBudgetById(String budgetId) {
        Optional<Budget> getBudgetId = budgetRepo.findById(budgetId);
        if (getBudgetId.isPresent()) {
            Budget budget = getBudgetId.get();
            BudgetResponse budgetResponse = new BudgetResponse();
            budgetResponse.setBudgetId(budget.getBudgetId());
            budgetResponse.setBudgetAmount(budget.getBudgetAmount());
            return budgetResponse;
        } else {
            throw new IllegalArgumentException("Budget not found");
        }
    }

    @Override
    public BudgetResponse updateBudget(String budgetId, BudgetRequest budgetRequest) {
        Optional<Budget> getBudgetId = budgetRepo.findById(budgetId);
        if (getBudgetId.isPresent()) {
            Budget budget = getBudgetId.get();
            budget.setBudgetAmount(budgetRequest.getBudgetAmount());
            Budget updatedBudget = budgetRepo.save(budget);

            BudgetResponse budgetResponse = new BudgetResponse();
            budgetResponse.setBudgetId(updatedBudget.getBudgetId());
            budgetResponse.setBudgetAmount(updatedBudget.getBudgetAmount());
            return budgetResponse;
        } else {
            throw new IllegalArgumentException("Budget not found");
        }
    }

    @Override
    public void deleteBudget(String budgetId) {
        if (budgetRepo.existsById(budgetId)) {
            budgetRepo.deleteById(budgetId);
        } else{
            throw new IllegalArgumentException("Budget not found");
        }
    }
}
