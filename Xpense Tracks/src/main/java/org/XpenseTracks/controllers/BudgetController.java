package org.XpenseTracks.controllers;

import org.XpenseTracks.dtos.request.BudgetRequest;
import org.XpenseTracks.dtos.response.BudgetResponse;
import org.XpenseTracks.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<BudgetResponse> createBudget(@RequestBody BudgetRequest budgetRequest) {
        BudgetResponse budgetResponse = budgetService.createBudget(budgetRequest);
        return ResponseEntity.ok(budgetResponse);
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<BudgetResponse> getBudgetById(@PathVariable String budgetId) {
        BudgetResponse budgetResponse = budgetService.getBudgetById(budgetId);
        if (budgetResponse == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetResponse);
    }

    @PutMapping("/{budgetId}")
    public ResponseEntity<BudgetResponse> updateBudget(@PathVariable String budgetId, @RequestBody BudgetRequest budgetRequest) {
        BudgetResponse budgetResponse = budgetService.updateBudget(budgetId, budgetRequest);
        if (budgetResponse == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(budgetResponse);
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<BudgetResponse> deleteBudget(@PathVariable String budgetId) {
        budgetService.deleteBudget(budgetId);
        return ResponseEntity.noContent().build();
    }
}
