package com.example.smart_expense_tracker.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String month;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}