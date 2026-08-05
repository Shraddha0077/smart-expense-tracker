package com.example.smart_expense_tracker.repository;

import com.example.smart_expense_tracker.entity.Budget;
import com.example.smart_expense_tracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByCategory(Category category);

    Optional<Budget> findByCategoryAndMonth(Category category, String month);
}