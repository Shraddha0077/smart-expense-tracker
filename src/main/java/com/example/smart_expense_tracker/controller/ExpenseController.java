package com.example.smart_expense_tracker.controller;

import com.example.smart_expense_tracker.dto.ExpenseDTO;
import com.example.smart_expense_tracker.entity.Expense;
import com.example.smart_expense_tracker.service.CategoryService;
import com.example.smart_expense_tracker.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final CategoryService categoryService;

    public ExpenseController(ExpenseService expenseService,
                             CategoryService categoryService) {
        this.expenseService = expenseService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String viewExpenses(Model model) {

        model.addAttribute("expenses",
                expenseService.getAllExpenses());

        model.addAttribute("categories",
                categoryService.getAllCategories());

        return "expenses";
    }

    @GetMapping("/add")
    public String showAddExpenseForm(Model model) {

        model.addAttribute("expense", new ExpenseDTO());

        model.addAttribute("categories",
                categoryService.getAllCategories());

        return "add-expense";
    }
    @PostMapping("/save")
    public String saveExpense(@ModelAttribute("expense") ExpenseDTO dto) {

        Expense expense = new Expense();

        expense.setTitle(dto.getTitle());

        expense.setAmount(dto.getAmount());

        expense.setExpenseDate(dto.getExpenseDate());

        expense.setDescription(dto.getDescription());

        expense.setCategory(
                categoryService.getCategoryById(dto.getCategoryId())
        );

        expenseService.saveExpense(expense);

        return "redirect:/expenses";
    }

    @GetMapping("/edit/{id}")
    public String showEditExpenseForm(@PathVariable Long id, Model model) {

        Expense expense = expenseService.getExpenseById(id);

        ExpenseDTO dto = new ExpenseDTO();

        dto.setId(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setExpenseDate(expense.getExpenseDate());
        dto.setDescription(expense.getDescription());

        if (expense.getCategory() != null) {
            dto.setCategoryId(expense.getCategory().getId());
        }

        model.addAttribute("expense", dto);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "edit-expense";
    }

    @PostMapping("/update")
    public String updateExpense(@ModelAttribute("expense") ExpenseDTO dto) {

        Expense expense = expenseService.getExpenseById(dto.getId());

        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setDescription(dto.getDescription());

        expense.setCategory(
                categoryService.getCategoryById(dto.getCategoryId())
        );

        expenseService.updateExpense(expense);

        return "redirect:/expenses";
    }
    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }

    @GetMapping("/search")
    public String searchExpenses(@RequestParam("keyword") String keyword,
                                 Model model) {

        model.addAttribute("expenses",
                expenseService.searchExpenses(keyword));

        return "expenses";
    }

    @GetMapping("/filter")
    public String filterExpenses(@RequestParam Long categoryId,
                                 Model model) {

        model.addAttribute("expenses",
                expenseService.getExpensesByCategory(categoryId));

        model.addAttribute("categories",
                categoryService.getAllCategories());

        return "expenses";
    }
}