package com.example.smart_expense_tracker.controller;

import com.example.smart_expense_tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportsController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/reports")
    public String reports(Model model) {

        model.addAttribute(
                "monthlyReport",
                expenseService.getMonthlyExpenseReport()
        );

        return "reports";
    }
}