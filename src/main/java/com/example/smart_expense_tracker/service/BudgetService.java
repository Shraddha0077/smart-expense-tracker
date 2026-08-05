package com.example.smart_expense_tracker.service;

import com.example.smart_expense_tracker.entity.Budget;

import java.util.List;

public interface BudgetService {

    List<Budget> getAllBudgets();

    Budget getBudgetById(Long id);

    Budget saveBudget(Budget budget);

    void deleteBudget(Long id);
}