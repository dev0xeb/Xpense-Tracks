package org.XpenseTracks.services;

import org.XpenseTracks.data.repository.BudgetRepo;
import org.XpenseTracks.dtos.request.BudgetRequest;
import org.XpenseTracks.dtos.response.BudgetResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
 public class BudgetServiceImplTest {
    @Autowired
    private BudgetServiceImpl budgetService;
    @Autowired
    private BudgetRepo budgetRepo;

    @BeforeEach
    public void setUp() {
        budgetRepo.deleteAll();
    }

    @Test
    public void testBudgetDefaultValueAtCreationIsZero() {
       BudgetRequest budgetRequest = new BudgetRequest();
       budgetRequest.setUserId("1");
       budgetRequest.setBudgetAmount(BigDecimal.ZERO);

       BudgetResponse budgetResponse = budgetService.createBudget(budgetRequest);
       assertNotNull(budgetResponse);
       assertEquals(BigDecimal.ZERO, budgetResponse.getBudgetAmount());
    }

    @Test
   public void testCreateBudget() {
       BudgetRequest budgetRequest = new BudgetRequest();
       budgetRequest.setUserId("1");
       budgetRequest.setBudgetAmount(BigDecimal.valueOf(1000));

       BudgetResponse budgetResponse = budgetService.createBudget(budgetRequest);
       assertEquals(BigDecimal.valueOf(1000), budgetResponse.getBudgetAmount());
    }

    @Test
   public void testGetBudgetById() {
       BudgetRequest budgetRequest = new BudgetRequest();
       budgetRequest.setUserId("1");
       budgetRequest.setBudgetAmount(BigDecimal.valueOf(1000));

       BudgetResponse budgetResponse = budgetService.createBudget(budgetRequest);
       BudgetResponse getBudget = budgetService.getBudgetById(budgetResponse.getBudgetId());
       assertEquals(budgetResponse.getBudgetId(), getBudget.getBudgetId());
    }

    @Test
    public void testToUpdateBudget() {
        BudgetRequest budgetRequest = new BudgetRequest();
        budgetRequest.setUserId("1");
        budgetRequest.setBudgetAmount(BigDecimal.valueOf(1000));

        BudgetResponse budgetResponse = budgetService.createBudget(budgetRequest);
        BudgetRequest updateBudget = new BudgetRequest();
        updateBudget.setBudgetAmount(BigDecimal.valueOf(3000));

        BudgetResponse updatedAmount = budgetService.updateBudget(budgetResponse.getBudgetId(), updateBudget);
        assertEquals(BigDecimal.valueOf(3000), updatedAmount.getBudgetAmount());
    }

    @Test
    public void testDeleteBudget() {
        BudgetRequest budgetRequest = new BudgetRequest();
        budgetRequest.setUserId("1");
        budgetRequest.setBudgetAmount(BigDecimal.valueOf(1000));

        BudgetResponse budgetResponse = budgetService.createBudget(budgetRequest);
        budgetService.deleteBudget(budgetResponse.getBudgetId());
        assertFalse(budgetRepo.existsById(budgetResponse.getBudgetId()));
    }

    @Test
    public void testCreateBudgetWithNullValue(){
        BudgetRequest budgetRequest = new BudgetRequest();
        budgetRequest.setUserId("1");

        Exception nullValueException = assertThrows(NullPointerException.class, ()-> {
            budgetService.createBudget(budgetRequest);
        });
       String exceptionMessage = "Budget amount must be greater than zero";
       String actualMessage = nullValueException.getMessage();

       assertEquals(exceptionMessage, actualMessage);
    }

    @Test
    public void testCreatBudgetWithEmptyUserId(){
        BudgetRequest budgetRequest = new BudgetRequest();
        budgetRequest.setUserId("");
        budgetRequest.setBudgetAmount(BigDecimal.ZERO);

        Exception nullIdException = assertThrows(IllegalArgumentException.class, ()-> {
            budgetService.createBudget(budgetRequest);
        });

        String exceptionMessage = "Invalid user id";
        String actualMessage = nullIdException.getMessage();
        assertEquals(exceptionMessage, actualMessage);
    }

    @Test
    public void testCreateBudgetWithNegativeAmount(){
        BudgetRequest budgetRequest = new BudgetRequest();
        budgetRequest.setUserId("1");
        budgetRequest.setBudgetAmount(BigDecimal.valueOf(-1000));

        Exception negativeAmountException = assertThrows(NullPointerException.class, ()-> {
            budgetService.createBudget(budgetRequest);
        });

        String exceptionMessage = "Budget amount must be greater than zero";
        String actualMessage = negativeAmountException.getMessage();
        assertEquals(exceptionMessage, actualMessage);
    }

    @Test
    public void testCreateDuplicateBudget(){
        BudgetRequest budgetRequest = new BudgetRequest();
        budgetRequest.setUserId("1");
        budgetRequest.setBudgetAmount(BigDecimal.valueOf(1000));

        budgetService.createBudget(budgetRequest);
        Exception duplicateException = assertThrows(IllegalArgumentException.class, ()-> {
            budgetService.createBudget(budgetRequest);
        });

        String duplicateExceptionMessage = "Budget already exists";
        String actualMessage =duplicateException.getMessage();
        assertEquals(duplicateExceptionMessage, actualMessage);
    }
}