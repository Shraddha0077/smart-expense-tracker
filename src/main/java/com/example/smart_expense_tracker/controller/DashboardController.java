package com.example.smart_expense_tracker.controller;

import com.example.smart_expense_tracker.entity.Expense;
import com.example.smart_expense_tracker.service.BudgetService;
import com.example.smart_expense_tracker.service.CategoryService;
import com.example.smart_expense_tracker.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final ExpenseService expenseService;
    private final CategoryService categoryService;
    private final BudgetService budgetService;

    public DashboardController(ExpenseService expenseService,
                               CategoryService categoryService,
                               BudgetService budgetService) {
        this.expenseService = expenseService;
        this.categoryService = categoryService;
        this.budgetService = budgetService;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {

        List<Expense> expenses = expenseService.getAllExpenses();

        double totalExpenses = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        double totalBudget = budgetService.getAllBudgets()
                .stream()
                .mapToDouble(budget -> budget.getAmount())
                .sum();

        double remainingBudget = totalBudget - totalExpenses;

        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("totalBudget", totalBudget);
        model.addAttribute("remainingBudget", remainingBudget);

        model.addAttribute("totalCategories",
                categoryService.getAllCategories().size());

        model.addAttribute("totalTransactions",
                expenses.size());

        model.addAttribute("recentExpenses", expenses);

        model.addAttribute("monthlyReport",
                expenseService.getMonthlyExpenseReport());

        return "dashboard";
    }
}