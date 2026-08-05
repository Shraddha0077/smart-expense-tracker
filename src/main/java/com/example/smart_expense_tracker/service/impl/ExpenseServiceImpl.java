package com.example.smart_expense_tracker.service.impl;

import com.example.smart_expense_tracker.dto.MonthlyExpenseDTO;
import com.example.smart_expense_tracker.entity.Category;
import com.example.smart_expense_tracker.entity.Expense;
import com.example.smart_expense_tracker.repository.CategoryRepository;
import com.example.smart_expense_tracker.repository.ExpenseRepository;
import com.example.smart_expense_tracker.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public List<Expense> searchExpenses(String title) {
        return expenseRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Expense> getExpensesByCategory(Long categoryId) {

        Category category = categoryRepository
                .findById(categoryId)
                .orElse(null);

        return expenseRepository.findByCategory(category);
    }

    @Override
    public List<MonthlyExpenseDTO> getMonthlyExpenseReport() {

        List<Object[]> result = expenseRepository.getMonthlyExpenseReport();

        List<MonthlyExpenseDTO> report = new ArrayList<>();

        for (Object[] row : result) {

            report.add(new MonthlyExpenseDTO(
                    row[0].toString(),
                    ((Number) row[1]).doubleValue()
            ));
        }

        return report;
    }
}