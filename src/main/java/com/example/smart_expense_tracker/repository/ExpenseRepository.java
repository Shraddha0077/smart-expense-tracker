package com.example.smart_expense_tracker.repository;

import com.example.smart_expense_tracker.entity.Category;
import com.example.smart_expense_tracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByTitleContainingIgnoreCase(String title);

    List<Expense> findByCategory(Category category);

    @Query(value = """
        SELECT DATE_FORMAT(expense_date, '%Y-%m') AS month,
               SUM(amount)
        FROM expenses
        GROUP BY DATE_FORMAT(expense_date, '%Y-%m')
        ORDER BY DATE_FORMAT(expense_date, '%Y-%m')
        """, nativeQuery = true)
    List<Object[]> getMonthlyExpenseReport();
}