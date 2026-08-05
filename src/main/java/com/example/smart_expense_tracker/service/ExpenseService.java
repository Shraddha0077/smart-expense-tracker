package com.example.smart_expense_tracker.service;

import com.example.smart_expense_tracker.dto.MonthlyExpenseDTO;
import com.example.smart_expense_tracker.entity.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense saveExpense(Expense expense);

    Expense updateExpense(Expense expense);

    void deleteExpense(Long id);

    List<Expense> searchExpenses(String title);
    List<Expense> getExpensesByCategory(Long categoryId);
    List<MonthlyExpenseDTO> getMonthlyExpenseReport();

}