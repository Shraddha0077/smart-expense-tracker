package com.example.smart_expense_tracker.controller;

import com.example.smart_expense_tracker.entity.Budget;
import com.example.smart_expense_tracker.service.BudgetService;
import com.example.smart_expense_tracker.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetService budgetService;
    private final CategoryService categoryService;

    public BudgetController(BudgetService budgetService,
                            CategoryService categoryService) {
        this.budgetService = budgetService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listBudgets(Model model) {
        model.addAttribute("budgets", budgetService.getAllBudgets());
        return "budget-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("budget", new Budget());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "budget-form";
    }

    @PostMapping("/save")
    public String saveBudget(@ModelAttribute Budget budget) {
        budgetService.saveBudget(budget);
        return "redirect:/budgets";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("budget", budgetService.getBudgetById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "budget-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return "redirect:/budgets";
    }
}